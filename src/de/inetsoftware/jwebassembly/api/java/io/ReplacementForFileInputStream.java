/*
 * Copyright 2022 - 2023 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.api.java.io;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.inetsoftware.jwebassembly.api.annotation.Partial;

/**
 * Replacement methods for the class java.io.FileInputStream.
 * 
 * @author Volker Berlin
 */
@Partial( "java/io/FileInputStream" )
class ReplacementForFileInputStream {

    /**
     * Replacement for constructor.
     */
    ReplacementForFileInputStream( File file ) throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    /**
     * Replacement for constructor.
     */
    ReplacementForFileInputStream( FileDescriptor fdObj ) throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    /**
     * Replacement for read(). Stops compiling via reference of InputStream.
     */
    int read() {
        return -1;
    }

    /**
     * Replacement for read(). Stops compiling via reference of InputStream.
     */
    int read( byte b[] ) {
        return -1;
    }

    /**
     * Replacement for read(). Stops compiling via reference of InputStream.
     */
    int read( byte b[], int off, int len ) {
        return -1;
    }

    /**
     * Replacement for available(). Stops compiling via reference of InputStream.
     */
    int available() {
        return 0;
    }

    /**
     * Replacement for skip(). Stops compiling via reference of InputStream.
     */
    long skip( long n ) {
        return 0;
    }

    /**
     * Replacement for close(). Stops compiling via reference of InputStream.
     */
    void close() {
        // nothing
    }

    /**
     * Replacement for native init code.
     */
    private static void initIDs() {
        // nothing
    }
}
