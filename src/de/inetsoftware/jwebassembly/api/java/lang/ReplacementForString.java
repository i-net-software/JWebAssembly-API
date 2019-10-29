/*
 * Copyright 2019 Volker Berlin (i-net software)
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

import de.inetsoftware.jwebassembly.api.annotation.Import;
import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.String.
 * 
 * @author Volker Berlin
 */
class ReplacementForString {

    /**
     * Replacement for new String(byte[])
     */
    @Import( name="newBytes", js = "(value) => new TextDecoder().decode(value)")
    @Replace( "java/lang/String.<init>([B)V" )
    static String init(byte[] value) {
        return null; // for compiler
    }

    /**
     * Replacement for new String(char[])
     */
    @Import( name="newChars", js = "(value)=>String.fromCharCode.apply(null,value)")
    @Replace( "java/lang/String.<init>([C)V" )
    static String init(char[] value) {
        return null; // for compiler
    }
}
