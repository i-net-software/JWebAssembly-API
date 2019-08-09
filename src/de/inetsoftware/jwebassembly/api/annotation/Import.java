/*
 * Copyright 2018 Volker Berlin (i-net software)
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
 * Mark a function as an import from host environment/JavaScript.
 * 
 * @author Volker Berlin
 *
 */
@Retention( RetentionPolicy.CLASS )
@Target( ElementType.METHOD )
public @interface Import {

    /**
     * The module/object name of the import. If not set then the simple class name is used.
     * 
     * @return the module name
     */
    String module() default "";

    /**
     * The function name in the scope of the module. If not set then the method name is used.
     * 
     * @return the name
     */
    String name() default "";

    /**
     * The JavaScript replacement. If empty then there must be a same naming object in JavaScript.
     * 
     * @return JavaScript replacement. This is the body of the function.
     */
    String js() default "";
}
