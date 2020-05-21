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
package de.inetsoftware.jwebassembly.api.java.lang;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.Thread.
 *
 * @author Volker Berlin
 */
class ReplacementForThread {

    private static Thread current;

    /**
     * Replacement for currentThread().
     */
    @Replace( "java/lang/Thread.currentThread()Ljava/lang/Thread;" )
    static Thread currentThread() {
        if( current == null ) {
            new Thread( "main" );
        }
        return current;
    }

    /**
     * Replacement for registerNatives().
     */
    @Replace( "java/lang/Thread.registerNatives()V" )
    static void registerNatives() {
    }

    /**
     * Replacement for setPriority0().
     */
    @Replace( "java/lang/Thread.setPriority0(I)V" )
    static void setPriority0( Thread THIS, int newPriority ) {
        // nothing
    }

    /**
     * Replacement for interrupt0().
     */
    @Replace( "java/lang/Thread.interrupt0()V" )
    static void interrupt0( Thread THIS ) {
        // nothing
    }
}
