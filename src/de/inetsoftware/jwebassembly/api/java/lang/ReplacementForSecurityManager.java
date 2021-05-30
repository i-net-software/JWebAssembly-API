/*
 * Copyright 2021 Volker Berlin (i-net software)
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

import java.security.Permission;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.SecurityManager.
 * 
 * @author Volker Berlin
 */
class ReplacementForSecurityManager {

    /**
     * Replacement for {@link SecurityManager#checkPermission(Permission)}
     */
    @Replace( "java/lang/SecurityManager.checkPermission(Ljava/security/Permission;)V" )
    public void checkPermission(Permission perm) {
        // nothing
    }
}
