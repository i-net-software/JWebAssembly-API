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
package de.inetsoftware.jwebassembly.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for a method that should replace a method of Java runtime. For example for a native method.
 * 
 * @author Volker Berlin
 */
@Retention( RetentionPolicy.CLASS )
@Target( {ElementType.METHOD, ElementType.CONSTRUCTOR} )
public @interface Replace {

    /**
     * The full signature of a method that should be replaced like "java/lang/Throwable.printStackTrace()V"
     * @return the signature
     */
    String value();
}
