/*
 * Copyright 2020 Volker Berlin (i-net software)
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

import javax.annotation.Nonnull;

import javafx.application.Application;
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
     * Start the emulator, load the html page and call the given main method.
     * 
     * @param htmlPage
     *             The resource of the html page that the WebAssembly contains.
     * @param main
     *            the executable with the main function
     */
    public static void launch( @Nonnull String htmlPage, @Nonnull Runnable main ) {
        launch( ClassLoader.getSystemResource( htmlPage ), main );
    }

    /**
     * Start the emulator, load the html page and call the given main method.
     * 
     * @param htmlPageURL
     *             The URL of the html page that the WebAssembly contains.
     * @param main
     *            the executable with the main function
     */
    public static void launch( @Nonnull URL htmlPageURL, @Nonnull Runnable main ) {
        JavaFxApplication.url = htmlPageURL.toString();
        JavaFxApplication.main = main;

        JavaFxApplication.launch( JavaFxApplication.class, new String[0] );
    }

    /**
     * The implementation of the javafx Application.
     * 
     * @author Volker Berlin
     */
    public static class JavaFxApplication extends Application {

        private static String    url;

        private static Runnable  main;

        private static WebEngine webEngine;

        private static JSObject  wasmImports;

        /**
         * register a JavaScript function from a nation method with annotation in the wasmimports
         * 
         * @param anno
         *            the annotation
         */
        static void registerScript( @Nonnull ImportAnnotation anno ) {
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

            return module.call( methodName, args );
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void start( Stage primaryStage ) throws Exception {
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
                    main.run();
                }
            } );
            // Load page
            webEngine.load( url );

            VBox vBox = new VBox( browser );
            Scene scene = new Scene( vBox );

            primaryStage.setTitle( "JWebAssembly Emulator" );
            primaryStage.setScene( scene );
            primaryStage.show();
        }
    }
}
