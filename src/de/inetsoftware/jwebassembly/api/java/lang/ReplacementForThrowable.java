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
}
