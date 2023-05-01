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
package de.inetsoftware.jwebassembly.api.java.lang.reflect;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.reflect.AccessibleObject.
 * 
 * @author Volker Berlin
 */
class ReplacementForAccessibleObject {

    /**
     * Replacement for static code.
     */
    @Replace( "java/lang/reflect/AccessibleObject.<clinit>()V" )
    private static void init() {
        // nothing
    }

    /**
     * Replacement for logIfOpenedForIllegalAccess().
     */
    @Replace( "java/lang/reflect/AccessibleObject.logIfOpenedForIllegalAccess(Ljava/lang/Class;Ljava/lang/Class;)V" )
    private void logIfOpenedForIllegalAccess( Class<?> caller, Class<?> declaringClass ) {
        // nothing
    }

    /**
     * Replacement for logIfExportedForIllegalAccess().
     */
    @Replace( "java/lang/reflect/AccessibleObject.logIfExportedForIllegalAccess(Ljava/lang/Class;Ljava/lang/Class;)V" )
    private void logIfExportedForIllegalAccess( Class<?> caller, Class<?> declaringClass ) {
        // nothing
    }
}
