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
package de.inetsoftware.jwebassembly.api.java.security;

import java.security.AccessControlContext;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.security.AccessController.
 *
 * @author Volker Berlin
 */
public class ReplacementForAccessController {

    /**
     * Replacement for doPrivileged().
     */
    @Replace( "java/security/AccessController.doPrivileged(Ljava/security/PrivilegedAction;)Ljava/lang/Object;" )
    private static <T> T doPrivileged( PrivilegedAction<T> action ) {
        return action.run();
    }

    /**
     * Replacement for doPrivileged().
     */
    @Replace( "java/security/AccessController.doPrivileged(Ljava/security/PrivilegedAction;Ljava/security/AccessControlContext;)Ljava/lang/Object;" )
    static <T> T doPrivileged(PrivilegedAction<T> action, AccessControlContext context) {
        return doPrivileged( action );
    }

    /**
     * Replacement for doPrivileged().
     */
    @Replace( "java/security/AccessController.doPrivileged(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;" )
    private static <T> T doPrivileged( PrivilegedExceptionAction<T> action ) throws PrivilegedActionException {
        try {
            return action.run();
        } catch( RuntimeException ex ) {
            throw ex;
        } catch( Exception ex ) {
            throw new PrivilegedActionException( ex );
        }
    }

    /**
     * Replacement for doPrivileged().
     */
    @Replace( "java/security/AccessController.doPrivileged(Ljava/security/PrivilegedExceptionAction;Ljava/security/AccessControlContext;)Ljava/lang/Object;" )
    private static <T> T doPrivileged( PrivilegedExceptionAction<T> action, AccessControlContext context ) throws PrivilegedActionException {
        return doPrivileged( action );
    }

    /**
     * Replacement for getStackAccessControlContext().
     */
    @Replace( "java/security/AccessController.getStackAccessControlContext()Ljava/security/AccessControlContext;" )
    private static AccessControlContext getStackAccessControlContext() {
        return null;
    }

    /**
     * Replacement for getInheritedAccessControlContext().
     */
    @Replace( "java/security/AccessController.getInheritedAccessControlContext()Ljava/security/AccessControlContext;" )
    private static AccessControlContext getInheritedAccessControlContext() {
        return null;
    }

    /**
     * Replacement for getInheritedAccessControlContext().
     */
    @Replace( "java/security/AccessController.checkPermission(Ljava/security/Permission;)V" )
    public static void checkPermission(Permission perm) {
        // nothing, there is no security check in WASM
    }
}
