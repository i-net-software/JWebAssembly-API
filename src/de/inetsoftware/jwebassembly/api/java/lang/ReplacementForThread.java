/*
 * Copyright 2020 - 2023 Volker Berlin (i-net software)
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
import de.inetsoftware.jwebassembly.api.annotation.WasmTextCode;

/**
 * Replacement methods for the class java.lang.Thread.
 *
 * @author Volker Berlin
 */
class ReplacementForThread {

    private static Thread current;

    /**
     * Replacement for registerNatives().
     */
    @Replace( "java/lang/Thread.registerNatives()V" )
    static void registerNatives() {
    }

    /**
     * Replacement for currentThread().
     */
    @Replace( "java/lang/Thread.currentThread()Ljava/lang/Thread;" )
    static Thread currentThread() {
        if( current == null ) {
            createCurrentThread( "main" );
        }
        return current;
    }

    /**
     * Create the main thread instance. The code is like:
     * <code> 
     * current = new Thread( new threadGroup(), name );
     * </code>
     * but it set the current value before the constructor is invoked
     * @param name the string of the thread name
     */
    @WasmTextCode( "struct.new_default java/lang/ThreadGroup " // create object ThreadGroup
                    + "local.tee 1 " // ThreadGroup
                    + "call $java/lang/ThreadGroup.<init>()V " // invoke default, private constructor
                    + "struct.new_default java/lang/Thread " // create object Thread
                    + "local.tee 2 " // Thread
                    + "global.set $de/inetsoftware/jwebassembly/api/java/lang/ReplacementForThread.current " // set the currentThread before calling the constructor
                    + "local.get 2 " // Thread
                    + "i32.const 5 " //
                    + "struct.set java/lang/Thread priority " // set priority to 5
                    + "local.get 2 " // Thread
                    + "local.get 1 " // ThreadGroup
                    + "local.get 0 " // name
                    + "call $java/lang/Thread.<init>(Ljava/lang/ThreadGroup;Ljava/lang/String;)V " // invoke Thread constructor
    )
    private static native void createCurrentThread( String name );

    /**
     * Replacement for yield().
     */
    @Replace( "java/lang/Thread.yield()V" )
    static void yield() {
        // nothing
    }

    /**
     * Replacement for getContextClassLoader().
     */
    @Replace( "java/lang/Thread.getContextClassLoader()Ljava/lang/ClassLoader;" )
    public ClassLoader getContextClassLoader() {
        return null;
    }

    /**
     * Replacement for holdsLock().
     */
    @Replace( "java/lang/Thread.holdsLock(Ljava/lang/Object;)Z" )
    public static boolean holdsLock(Object obj) {
        return true;
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

    /**
     * Replacement for interrupt0().
     */
    @Replace( "java/lang/Thread.isInterrupted(Z)Z" )
    boolean isInterrupted(boolean ClearInterrupted) {
        return false;
    }

    /**
     * Replacement for interrupt0().
     */
    @Replace( "java/lang/Thread.start()V" )
    static void start() {
        throw new UnsupportedOperationException();
    }
}
