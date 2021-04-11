/*
 * Copyright 2020 - 2021 Volker Berlin (i-net software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.inetsoftware.jwebassembly.emulator;

import java.net.URL;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Nonnull;

import de.inetsoftware.jwebassembly.web.DOMString;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

/**
 * The main class start point for the emulator.
 * 
 * @author Volker Berlin
 */
@SuppressWarnings( "restriction" )
public class JWebAssemblyEmulator {

    static {
        // move the JavaScript console to System.out
        com.sun.javafx.webkit.WebConsoleListener.setDefaultListener( ( webView, message, lineNumber, sourceId ) -> {
            System.out.println( message + "[at " + lineNumber + "]" );
        } );
    }

    /**
     * Start the emulator from a resource file, load the html page and call the given main method.
     * 
     * @param htmlPage
     *             The resource of the html page that the WebAssembly contains.
     * @param main
     *            the executable with the main function
     */
    public static void launchResource( @Nonnull String htmlPage, @Nonnull Runnable main ) {
        URL resource = ClassLoader.getSystemResource( htmlPage );
        Objects.requireNonNull( resource, "Resource not found for: " + htmlPage );
        launch( resource.toString(), null, main );
    }

    /**
     * Start the emulator from a URL, load the html page and call the given main method.
     * 
     * @param htmlPageURL
     *             The URL of the html page that the WebAssembly contains.
     * @param main
     *            the executable with the main function
     */
    public static void launchURL( @Nonnull URL htmlPageURL, @Nonnull Runnable main ) {
        Objects.requireNonNull( htmlPageURL, "URL of HTML page is null." );
        launch( htmlPageURL.toString(), null, main );
    }

    /**
     * Start the emulator from a URL, load the html page and call the given main method.
     * 
     * @param content
     *             The content of the html page that the WebAssembly contains.
     * @param main
     *            the executable with the main function
     */
    public static void launchContent( @Nonnull String content, @Nonnull Runnable main ) {
        Objects.requireNonNull( content, "Content of HTML page is null." );
        launch( null, content, main );
    }

    /**
     * Start the emulator.
     * 
     * @param htmlPageURL
     *            The URL of the html page that the WebAssembly contains.
     * @param content
     *            The content of the html page that the WebAssembly contains.
     * @param main
     *            the executable with the main function
     */
    private static void launch( String htmlPageURL, String content, @Nonnull Runnable main ) {
        JavaFxApplication.url = htmlPageURL;
        JavaFxApplication.content = content;
        JavaFxApplication.main = main;
        JavaFxApplication.error = null;

        if( JavaFxApplication.stage == null ) {
            //we start a new thread because JavaFX is blocking
            Thread thread = new Thread() {
                public void run() {
                    try {
                        JavaFxApplication.launch( JavaFxApplication.class, new String[0] );
                    } catch( Throwable th ) {
                        JavaFxApplication.error = th;
                    }
                }
            };
            thread.start();
        }
        while( JavaFxApplication.error == null && JavaFxApplication.stage == null ) {
            try {
                Thread.sleep( 1 );
            } catch( InterruptedException th ) {
                JavaFxApplication.error = th;
            }
        }
        if( JavaFxApplication.error == null ) {
            CountDownLatch launchLatch = new CountDownLatch( 1 );
            Platform.runLater( () -> {
                try {
                    JavaFxApplication.execute();
                } catch( Throwable th ) {
                    JavaFxApplication.error = th;
                }
                launchLatch.countDown();
            });
            try {
                launchLatch.await();
            } catch( InterruptedException th ) {
                JavaFxApplication.error = th;
            }
        }
        if( JavaFxApplication.error != null ) {
            throwAny( JavaFxApplication.error );
        }
    }

    /**
     * Hide the emulator window
     */
    static void hide() {
        Platform.setImplicitExit(false);
        JavaFxApplication.stage.hide();
    }

    /**
     * Throws any (checked) exception without in signature
     * 
     * @param e the exception
     * @param <E> any Throwable
     * @throws E any Throwable
     */
    static <E extends Throwable> void throwAny( Throwable e ) throws E {
        throw (E)e;
    }

    /**
     * The implementation of the javafx Application.
     * 
     * @author Volker Berlin
     */
    public static class JavaFxApplication extends Application {

        private static String    url;

        private static String    content;

        private static Runnable  main;

        private static Stage     stage;

        private static WebEngine webEngine;

        private static JSObject  wasmImports;

        private static Throwable error;

        private static final Set<ImportAnnotation> ANNOTATIONS = ConcurrentHashMap.newKeySet();

        /**
         * register a JavaScript function from a nation method with annotation in the wasmimports
         * 
         * @param anno
         *            the annotation
         */
        static void registerScript( @Nonnull ImportAnnotation anno ) {
            if( wasmImports == null ) {
                // A class with native code was loaded before launching, can occur with JUnit testing
                JWebAssemblyEmulator.launch( url, content, () -> {} );
            }
            ANNOTATIONS.add( anno );
            if( !Platform.isFxApplicationThread() ) {
                // A class with native code was loaded outside of the event thread
                Platform.runLater( () -> registerScript( anno ) );
                return;
            }

            Object obj = wasmImports.getMember( anno.module );
            if( "undefined".equals( obj ) ) {
                webEngine.executeScript( "wasmImports." + anno.module + " = {}" );
            }

            webEngine.executeScript( "wasmImports." + anno.module + "." + anno.name + "=" + anno.javaScript );
        }

        /**
         * The bridge method for the WebAssembly import function into the JavaScript.
         * 
         * @param moduleName
         *            the name of the module
         * @param methodName
         *            the name of the function
         * @param args
         *            the arguments
         * @return the return value if any
         */
        public static Object executeScript( String moduleName, String methodName, Object... args ) {
            JSObject module = (JSObject)wasmImports.getMember( moduleName );

            if( args != null ) {
                // JavaFX does not support our marker interface DOMString that 
                for( int i = 0, length = args.length; i < length; i++ ) {
                    if( args[i] instanceof DOMString ) {
                        args[i] = args[i].toString();
                    }
                }
            }
            return module.call( methodName, args );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void start( Stage primaryStage ) throws Exception {
            stage = primaryStage;
        }

        /**
         * Load the page and run the start method
         */
        public static void execute() {
            // Create a WebView
            WebView browser = new WebView();

            // Get WebEngine via WebView
            webEngine = browser.getEngine();
            // https://stackoverflow.com/questions/41654573/java-fx-javascript
            Worker<Void> worker = webEngine.getLoadWorker();
            worker.stateProperty().addListener( ( obs, old, neww ) -> {
                if( neww == Worker.State.SUCCEEDED ) {
                    try {
                        wasmImports = (JSObject)webEngine.executeScript( "wasmImports" );
                    } catch( JSException e ) {
                        webEngine.executeScript( "var wasmImports = {}" );
                        wasmImports = (JSObject)webEngine.executeScript( "wasmImports" );
                    }
                    // recreate the annotation in a new page
                    for( ImportAnnotation anno : ANNOTATIONS ) {
                        registerScript( anno );
                    }
                    try {
                        main.run();
                    } catch( Throwable th ) {
                        error = th;
                    }
                    //primaryStage.close();
                }
            } );
            // Load page
            if( content != null ) {
                webEngine.loadContent( content );
            } else {
                webEngine.load( url );
            }

            VBox vBox = new VBox( browser );
            Scene scene = new Scene( vBox );

            stage.setTitle( "JWebAssembly Emulator" );
            stage.setScene( scene );
            stage.show();
        }

    }
}
