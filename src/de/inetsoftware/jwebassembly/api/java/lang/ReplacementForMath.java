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

    /**
     * Replacement for {@link Math#atan2(double, double)}
     * 
     * @param y
     *            the ordinate coordinate
     * @param x
     *            the abscissa coordinate
     * @return the theta component of the point(r, theta)in polar coordinates that corresponds to the point(x, y) in
     *         Cartesian coordinates.
     */
    @Replace( "java/lang/Math.atan2(DD)D" )
    @Import( module = "Math", name = "atan2" )
    static double atan2( double y, double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#pow(double, double)}
     * 
     * @param   a   the base.
     * @param   b   the exponent.
     * @return  the value a<sup>b</sup>.
      */
    @Replace( "java/lang/Math.pow(DD)D" )
    @Import( module = "Math", name = "pow" )
    static double pow( double a, double b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#random()}
     * 
     * @return a pseudorandom double greater than or equal 0.0 and less than 1.0.
     */
    @Replace( "java/lang/Math.random()D" )
    @Import( module = "Math", name = "random" )
    static double random() {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#abs(double)}
     * 
     * @param x
     *            the value.
     * @return the absolute value of the argument.
     */
    @Replace( "java/lang/Math.abs(D)D" )
    @WasmTextCode( "local.get 0 " //
                    + "f64.abs " //
                    + "return" )
    static double abs( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#abs(float)}
     * 
     * @param x
     *            the value.
     * @return the absolute value of the argument.
     */
    @Replace( "java/lang/Math.abs(F)F" )
    @WasmTextCode( "local.get 0 " //
                    + "f32.abs " //
                    + "return" )
    static float abs( float x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#max(double,double)}
     * 
     * @param   a   an argument.
     * @param   b   another argument.
     * @return the larger of a and b.
     */
    @Replace( "java/lang/Math.max(DD)D" )
    @WasmTextCode( "local.get 0 " //
                    + "local.get 1 " //
                    + "f64.max " //
                    + "return" )
    static double max( double a, double b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#max(float,float)}
     * 
     * @param   a   an argument.
     * @param   b   another argument.
     * @return the larger of a and b.
     */
    @Replace( "java/lang/Math.max(FF)F" )
    @WasmTextCode( "local.get 0 " //
                    + "local.get 1 " //
                    + "f32.max " //
                    + "return" )
    static float max( float a, float b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#min(double,double)}
     * 
     * @param   a   an argument.
     * @param   b   another argument.
     * @return the smaller of a and b.
     */
    @Replace( "java/lang/Math.min(DD)D" )
    @WasmTextCode( "local.get 0 " //
                    + "local.get 1 " //
                    + "f64.min " //
                    + "return" )
    static double min( double a, double b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#min(float,float)}
     * 
     * @param   a   an argument.
     * @param   b   another argument.
     * @return the smaller of a and b.
     */
    @Replace( "java/lang/Math.min(FF)F" )
    @WasmTextCode( "local.get 0 " //
                    + "local.get 1 " //
                    + "f32.min " //
                    + "return" )
    static float min( float a, float b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#sinh(double)}
     * 
     * @param x
     *            The number whose hyperbolic sine is to be returned.
     * @return The hyperbolic sine of the argument.
     */
    @Replace( "java/lang/Math.sinh(D)D" )
    @Import( module = "Math", name = "sinh" )
    static double sinh( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#cosh(double)}
     * 
     * @param x
     *            The number whose hyperbolic cosine is to be returned.
     * @return The hyperbolic cosine of the argument.
     */
    @Replace( "java/lang/Math.cosh(D)D" )
    @Import( module = "Math", name = "cosh" )
    static double cosh( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#tanh(double)}
     * 
     * @param x
     *            The number whose hyperbolic tangent is to be returned.
     * @return The hyperbolic tangent of the argument.
     */
    @Replace( "java/lang/Math.tanh(D)D" )
    @Import( module = "Math", name = "tanh" )
    static double tanh( double x ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#hypot(double, double)}
     * 
     * @param a
     *            a value
     * @param b
     *            a value
     * @return sqrt(a<sup>2</sup>+b<sup>2</sup>)
     */
    @Replace( "java/lang/Math.hypot(DD)D" )
    @Import( module = "Math", name = "hypot" )
    static double hypot( double a, double b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#expm1(double)}
     * 
     * @param x
     *            the exponent
     * @return the value e<sup>x</sup> - 1.
     */
    @Replace( "java/lang/Math.expm1(D)D" )
    @Import( module = "Math", name = "expm1" )
    static double expm1( double a, double b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#log1p(double)}
     * 
     * @param x
     *            a value
     * @return the result
     */
    @Replace( "java/lang/Math.log1p(D)D" )
    @Import( module = "Math", name = "log1p" )
    static double log1p( double a, double b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#copySign(double,double)}
     * 
     * @param   a   an argument.
     * @param   b   another argument.
     * @return the result
     */
    @Replace( "java/lang/Math.copySign(DD)D" )
    @WasmTextCode( "local.get 0 " //
                    + "local.get 1 " //
                    + "f64.copysign " //
                    + "return" )
    static double copySign( double a, double b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for {@link Math#copySign(float,float)}
     * 
     * @param   a   an argument.
     * @param   b   another argument.
     * @return the result
     */
    @Replace( "java/lang/Math.copySign(FF)F" )
    @WasmTextCode( "local.get 0 " //
                    + "local.get 1 " //
                    + "f32.copysign " //
                    + "return" )
    static float copySign( float a, float b ) {
        return 0; // for Java compiler
    }

}
