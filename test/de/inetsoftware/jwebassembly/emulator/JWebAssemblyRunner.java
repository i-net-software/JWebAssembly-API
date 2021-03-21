/*
 * Copyright 2021 Volker Berlin (i-net software)
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

import java.util.concurrent.CountDownLatch;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * A JUnit runner to run JWebEngine tests with the emulator
 *
 * @author Volker Berlin
 */
public class JWebAssemblyRunner extends BlockJUnit4ClassRunner {

    /**
     * Create an instance
     * 
     * @param testClass
     *            the test class
     * @throws InitializationError
     *             if the test class is malformed.
     */
    public JWebAssemblyRunner( Class<?> testClass ) throws InitializationError {
        super( testClass );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Statement methodInvoker( FrameworkMethod method, Object test ) {
        Statement statement = super.methodInvoker( method, test );

        return new Statement() {
            private Throwable error;

            /**
             * {@inheritDoc}
             */
            @Override
            public void evaluate() throws Throwable {
                CountDownLatch launchLatch = new CountDownLatch( 1 );
                // show the emulator and run the test
                JWebAssemblyEmulator.launchContent( "", () -> {
                    try {
                        statement.evaluate();
                    } catch( Throwable th ) {
                        error = th;
                    }
                    JWebAssemblyEmulator.hide();
                    launchLatch.countDown();
                } );
                // wait that the test finished
                launchLatch.await();
                if( error != null ) {
                    JWebAssemblyEmulator.throwAny( error );
                }
            }
        };
    }
}
