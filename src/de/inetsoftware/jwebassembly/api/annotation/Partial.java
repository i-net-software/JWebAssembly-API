/*
 * Copyright 2020 - 2026 Volker Berlin (i-net software)
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
 * Mark a class as an extension/partial of an existing class. This is more powerful as {@link Replace}.
 * <ul>
 * <li>Every method with the same signature will be replaced.
 * <li>Native methods without a {@link WasmTextCode} annotation can be used as placeholder for invoking the method.
 * <li>Static constructors (&lt;clinit&gt;) will not replaced by default. A separate {@link Replace} is required.
 * </ul>
 * 
 * @author Volker Berlin
 *
 */
@Retention( RetentionPolicy.CLASS )
@Target( { ElementType.TYPE } )
public @interface Partial {

    /**
     * The full signature of a class that should be extended like "java/lang/String"
     * 
     * @return the signature
     */
    String value();

    /**
     * The minimum Java version (inclusive) for which this partial replacement should be applied. The default 0 means no constraint.
     * @return the minimum version
     */
    int minVersion() default 0;

    /**
     * The maximum Java version (inclusive) for which this partial replacement should be applied. The default 0 means no constraint.
     * @return the maximum version
     */
    int maxVersion() default 0;
}
