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

import java.util.Arrays;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.StringCoding.
 * 
 * @author Volker Berlin
 */
public class ReplacementForStringCoding {

    /**
     * Replacement for StringCoding.decode(byte[] ba, int off, int len) in Java 8. Decode UTF8 bytes to unicode 16 chars.
     */
    @Replace( "java/lang/StringCoding.decode([BII)[C" )
    private static char[] decode(byte[] bytes, int offset, int length) {
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

        return count == length ? buffer : Arrays.copyOf( buffer, count );
    }
}
