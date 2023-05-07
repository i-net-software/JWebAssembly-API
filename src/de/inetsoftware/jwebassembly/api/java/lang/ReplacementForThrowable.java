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

import java.io.PrintStream;
import java.io.PrintWriter;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.Throwable.
 * 
 * @author Volker Berlin
 */
class ReplacementForThrowable {

    /**
     * Replacement for the default constructor of Throwable
     */
    @Replace( "java/lang/Throwable.<init>()V" )
    ReplacementForThrowable() {
        // nothing
    }

    /**
     * Replacement for fillInStackTrace()
     */
    @Replace( "java/lang/Throwable.fillInStackTrace()Ljava/lang/Throwable;" )
    private static Throwable fillInStackTrace( Throwable this_ ) {
        return this_;
    }

    /**
     * Replacement for getStackTraceDepth()
     */
    @Replace( "java/lang/Throwable.getStackTraceDepth()I" )
    private int getStackTraceDepth() {
        return 0;
    }

    /**
     * Replacement for getStackTraceElement(int)
     */
    @Replace( "java/lang/Throwable.getStackTraceElement(I)Ljava/lang/StackTraceElement;" )
    StackTraceElement getStackTraceElement(int index) {
        return null;
    }

    /**
     * Replacement for printStackTrace(PrintStream)
     */
    @Replace( "java/lang/Throwable.printStackTrace(Ljava/io/PrintStream;)V" )
    void printStackTrace( PrintStream s ) {
        s.println( this );
    }

    /**
     * Replacement for printStackTrace(PrintWriter)
     */
    @Replace( "java/lang/Throwable.printStackTrace(Ljava/io/PrintWriter;)V" )
    void printStackTrace( PrintWriter s ) {
        s.println( this );
    }

    /**
     * Replacement for getStackTrace()
     */
    @Replace( "java/lang/Throwable.getStackTrace()[Ljava/lang/StackTraceElement;" )
    StackTraceElement[] getStackTrace() {
        return new StackTraceElement[0];
    }
}
