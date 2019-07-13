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
import de.inetsoftware.jwebassembly.api.annotation.WasmTextCode;

/**
 * Replacement methods for the class java.lang.Math.
 * 
 * @author Volker Berlin
 */
@SuppressWarnings( "unused" )
class ReplacementForMath {

    /**
     * Replacement for {@link Math#sin(double)}
     * 
     * @param x
     *            an angle, in radians.
     * @return the sine of the argument.
     */
    @Replace( "java/lang/Math.sin(D)D" )
    @Import( module = "Math", name = "sin" )
    static double sin( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#cos(double)}
     * 
     * @param x
     *            an angle, in radians.
     * @return the cosine of the argument.
     */
    @Replace( "java/lang/Math.cos(D)D" )
    @Import( module = "Math", name = "cos" )
    static double cos( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#tan(double)}
     * 
     * @param x
     *            an angle, in radians.
     * @return the tangent of the argument.
     */
    @Replace( "java/lang/Math.tan(D)D" )
    @Import( module = "Math", name = "tan" )
    static double tan( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#asin(double)}
     * 
     * @param x
     *            the value.
     * @return the arc sine of the argument.
     */
    @Replace( "java/lang/Math.asin(D)D" )
    @Import( module = "Math", name = "asin" )
    static double asin( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#acos(double)}
     * 
     * @param x
     *            the value.
     * @return the arc cosine of the argument.
     */
    @Replace( "java/lang/Math.acos(D)D" )
    @Import( module = "Math", name = "acos" )
    static double acos( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#atan(double)}
     * 
     * @param x
     *            the value.
     * @return the arc tangent of the argument.
     */
    @Replace( "java/lang/Math.atan(D)D" )
    @Import( module = "Math", name = "atan" )
    static double atan( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#exp(double)}
     * 
     * @param x
     *            the value.
     * @return the value <i>e</i><sup>x</sup>
     */
    @Replace( "java/lang/Math.exp(D)D" )
    @Import( module = "Math", name = "exp" )
    static double exp( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#log(double)}
     * 
     * @param x
     *            the value.
     * @return the value ln x
     */
    @Replace( "java/lang/Math.log(D)D" )
    @Import( module = "Math", name = "log" )
    static double log( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#log10(double)}
     * 
     * @param x
     *            the value.
     * @return the base 10 logarithm of x
     */
    @Replace( "java/lang/Math.log10(D)D" )
    @Import( module = "Math", name = "log10" )
    static double log10( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#sqrt(double)}
     * 
     * @param x
     *            the value.
     * @return the positive square root of x
     */
    @Replace( "java/lang/Math.sqrt(D)D" )
    @WasmTextCode( "local.get 0 " //
                    + "f64.sqrt " //
                    + "return" )
    static double sqrt( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#cbrt(double)}
     * 
     * @param x
     *            the value.
     * @return the cube root of x
     */
    @Replace( "java/lang/Math.cbrt(D)D" )
    @Import( module = "Math", name = "cbrt" )
    static double cbrt( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#IEEEremainder(double,double)}
     * 
     * @param   f1   the dividend.
     * @param   f2   the divisor.
     * @return  the remainder when f1 is divided by f2.
     */
    @Replace( "java/lang/Math.IEEEremainder(DD)D" )
    static double IEEEremainder( double f1, double f2 ) {
        return f1 - (f2 * Math.rint(f1 / f2) );
    }

    /**
     * Replacement for {@link Math#ceil(double)}
     * 
     * @param x
     *            the value.
     * @return the smallest (closest to negative infinity) integer value
     */
    @Replace( "java/lang/Math.ceil(D)D" )
    @WasmTextCode( "local.get 0 " //
                    + "f64.ceil " //
                    + "return" )
    static double ceil( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#floor(double)}
     * 
     * @param x
     *            the value.
     * @return the largest (closest to positive infinity) integer value
     */
    @Replace( "java/lang/Math.floor(D)D" )
    @WasmTextCode( "local.get 0 " //
                    + "f64.floor " //
                    + "return" )
    static double floor( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#rint(double)}
     * 
     * @param x
     *            the value.
     * @return the closest floating-point integer value
     */
    @Replace( "java/lang/Math.rint(D)D" )
    @WasmTextCode( "local.get 0 " //
                    + "f64.nearest " //
                    + "return" )
    static double rint( double x ) {
        return 0; // for Java compiler
    }
}
