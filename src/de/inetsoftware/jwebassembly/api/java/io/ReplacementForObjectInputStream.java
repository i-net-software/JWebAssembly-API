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

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.io.ObjectInputStream.
 * 
 * @author Volker Berlin
 */
public class ReplacementForObjectInputStream {

    /**
     * Replacement for readObject(). Java serializing required reflection which is currently not supported.
     */
    @Replace( "java/io/ObjectInputStream.readObject()Ljava/lang/Object;" )
    Object readObject() {
        throw new UnsupportedOperationException();
    }

    /**
     * Replacement for readObject(Class). Java serializing required reflection which is currently not supported.
     */
    @Replace( "java/io/ObjectInputStream.readObject(Ljava/lang/Class;)Ljava/lang/Object;" )
    private final Object readObject(Class<?> type) {
        throw new UnsupportedOperationException();
    }

    /**
     * Replacement for filterCheck(Class,int). Java serializing required reflection which is currently not supported.
     */
    @Replace( "java/io/ObjectInputStream.filterCheck(Ljava/lang/Class;I)V" )
    private void filterCheck( Class<?> clazz, int arrayLength ) {
    }
}
