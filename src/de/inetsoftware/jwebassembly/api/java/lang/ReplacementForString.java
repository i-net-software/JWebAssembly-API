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
    @Replace( "java/lang/String.<init>([B)V" )
    private static String newFromBytes(byte[] bytes) {
        return newFromSubBytes( bytes, 0, bytes.length );
    }

    /**
     * Replacement for new String(byte[],int,int). Decode the bytes with the platform default encoding UTF-8.
     */
    @Replace( "java/lang/String.<init>([BII)V" )
    private static String newFromSubBytes( byte[] bytes, int offset, int length ) {
        int count = 0;
        char[] buffer = new char[length - offset];
        while( offset < length ) {
            int ch = bytes[offset++] & 0xFF;

            if( ch <= 0x7F ) {
                //ch = ch;
            } else if( ch <= 0xDF ) {
                ch = ((ch & 0x1F) << 6) | (bytes[offset++] & 0x3F);
            } else if( ch <= 0xEF ) {
                ch = ((ch & 0x0F) << 12) | ((bytes[offset++] & 0x3F) << 6) | (bytes[offset++] & 0x3F);
            } else {
                ch = ((ch & 0x07) << 18) | ((bytes[offset++] & 0x3F) << 12) | ((bytes[offset++] & 0x3F) << 6) | (bytes[offset++] & 0x3F);
                // high surrogate
                buffer[count++] = (char)(0xD7C0 + ((ch >> 10) & 0x3ff));
                // low surrogate
                ch = 0xDC00 + (ch & 0x3ff);
            }

            buffer[count++] = (char)ch;
        }

        return new String( buffer, 0, count );
    }

    /**
     * Replacement for new String(char[])
     */
    @Replace( "java/lang/String.<init>([C)V" )
    static String newFromChars(char[] value) {
        // does not call the replacement method directly. Else the code will compiled two times.
        return new String( value, 0, value.length );
    }

    /**
     * Replacement for new String(char[],int,int)
     * If module and name is not set then the original from the replaced method is used.
     */
    @Import( module = "StringHelper", name = "newFromSubChars", js = "(value,off,count)=>{" + //
                    "var s='';" + //
                    "for(var i=off;i<off+count;i++){" + //
                    "s+=String.fromCharCode(value[i]);" + //
                    "}" + //
                    "return s}" )
    @Replace( "java/lang/String.<init>([CII)V" )
    static String newFromSubChars(char[] value, int offset, int count) {
        return null; // for compiler
    }
}
