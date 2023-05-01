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

import java.net.URL;
import java.nio.ByteBuffer;
import java.security.ProtectionDomain;
import java.util.Collections;
import java.util.Enumeration;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.ClassLoader.
 * 
 * @author Volker Berlin
 */
class ReplacementForClassLoader {

    /**
     * Replacement for static constructor();
     */
    @Replace( "java/lang/ClassLoader.<clinit>()V" )
    private static void init() {
        // nothing
    }

    /**
     * Replacement for registerNatives();
     */
    @Replace( "java/lang/ClassLoader.registerNatives()V" )
    private static void registerNatives() {
        // nothing
    }

    /**
     * Replacement for getSystemResources(String);
     */
    @Replace( "java/lang/ClassLoader.getResources(Ljava/lang/String;)Ljava/util/Enumeration;" )
    public Enumeration<URL> getResources(String name) {
        return Collections.emptyEnumeration();
    }

    /**
     * Replacement for getSystemResources(String);
     */
    @Replace( "java/lang/ClassLoader.getSystemResources(Ljava/lang/String;)Ljava/util/Enumeration;" )
    public static Enumeration<URL> getSystemResources(String name) {
        return Collections.emptyEnumeration();
    }

    /**
     * Replacement for getSystemClassLoader();
     */
    @Replace( "java/lang/ClassLoader.getSystemClassLoader()Ljava/lang/ClassLoader;" )
    public static ClassLoader getSystemClassLoader() {
        return null;
    }

    /**
     * Replacement for getPlatformClassLoader();
     */
    @Replace( "java/lang/ClassLoader.getPlatformClassLoader()Ljava/lang/ClassLoader;" )
    public static ClassLoader getPlatformClassLoader() {
        return null;
    }

    /**
     * Replacement for loadClass(String,boolean);
     */
    @Replace( "java/lang/ClassLoader.loadClass(Ljava/lang/String;Z)Ljava/lang/Class;" )
    Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return Class.forName( name, resolve, null );
    }

    /**
     * Replacement for registerAsParallelCapable();
     */
    @Replace( "java/lang/ClassLoader.registerAsParallelCapable()Z" )
    static boolean registerAsParallelCapable() {
        return false;
    }

    /**
     * Replacement for findLoadedClass(String);
     */
    @Replace( "java/lang/ClassLoader.findLoadedClass(Ljava/lang/String;)Ljava/lang/Class;" )
    Class<?> findLoadedClass(String name) {
        return null;
    }

    /**
     * Replacement for defineClass(String,byte[],int,int,ProtectionDomain);
     */
    @Replace( "java/lang/ClassLoader.defineClass(Ljava/lang/String;[BIILjava/security/ProtectionDomain;)Ljava/lang/Class;" )
    protected final Class<?> defineClass( String name, byte[] b, int off, int len, ProtectionDomain protectionDomain ) {
        throw new NoClassDefFoundError();
    }

    /**
     * Replacement for defineClass(String,ByteBuffer,ProtectionDomain);
     */
    @Replace( "java/lang/ClassLoader.defineClass(Ljava/lang/String;Ljava/nio/ByteBuffer;Ljava/security/ProtectionDomain;)Ljava/lang/Class;" )
    protected final Class<?> defineClass( String name, ByteBuffer b, ProtectionDomain protectionDomain ) {
        throw new NoClassDefFoundError();
    }
}
