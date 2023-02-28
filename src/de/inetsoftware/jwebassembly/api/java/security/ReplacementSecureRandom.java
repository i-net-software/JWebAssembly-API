/*
 * Copyright 2023 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.api.java.security;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.security.SecureRandom.
 *
 * @author Volker Berlin
 */
@Replace( "java/security/SecureRandom" )
class ReplacementSecureRandom extends java.util.Random {

    /**
     * Replacement for getSeed().
     */
    public static byte[] getSeed( int numBytes ) {
        return new ReplacementSecureRandom().generateSeed( numBytes );
    }

    /**
     * Replacement for generateSeed(int).
     */
    public byte[] generateSeed( int numBytes ) {
        byte[] bytes = new byte[numBytes];
        nextBytes( bytes );
        return bytes;
    }
}
