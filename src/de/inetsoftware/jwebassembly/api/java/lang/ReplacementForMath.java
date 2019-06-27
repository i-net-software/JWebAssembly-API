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
package de.inetsoftware.jwebassembly.api.java.lang;

import de.inetsoftware.jwebassembly.api.annotation.Import;
import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.Math.
 * 
 * @author Volker Berlin
 */
class ReplacementForMath {

    /**
     * Replacement for {@link Math#sin(double)}
     * 
     * @param a
     *            an angle, in radians.
     * @return the sine of the argument.
     */
    @Replace( "java/lang/Math.sin(D)D" )
    @Import( module = "Math", name = "sin" )
    static double sin( double a ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#cos(double)}
     * 
     * @param a
     *            an angle, in radians.
     * @return the cosine of the argument.
     */
    @Replace( "java/lang/Math.cos(D)D" )
    @Import( module = "Math", name = "cos" )
    static double cos( double a ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#tan(double)}
     * 
     * @param a
     *            an angle, in radians.
     * @return the tangent of the argument.
     */
    @Replace( "java/lang/Math.tan(D)D" )
    @Import( module = "Math", name = "tan" )
    static double tan( double a ) {
        return 0; // for Java compiler
    }
}
