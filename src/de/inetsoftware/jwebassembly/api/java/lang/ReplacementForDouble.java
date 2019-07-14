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

import de.inetsoftware.jwebassembly.api.annotation.Replace;
import de.inetsoftware.jwebassembly.api.annotation.WasmTextCode;

/**
 * Replacement methods for the class java.lang.Double.
 * 
 * @author Volker Berlin
 */
class ReplacementForDouble {

    /**
     * Replacement for Double.doubleToRawLongBits(double)
     */
    @Replace( "java/lang/Double.doubleToRawLongBits(D)J" )
    @WasmTextCode( "local.get 0 " //
                    + "i64.reinterpret_f64 " //
                    + "return" )
    static long doubleToRawLongBits(double value) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for Double.longBitsToDouble(long)
     */
    @Replace( "java/lang/Double.longBitsToDouble(J)D" )
    @WasmTextCode( "local.get 0 " //
                    + "f64.reinterpret_i64 " //
                    + "return" )
    static double longBitsToDouble(long value) {
        return 0; // for Java compiler
    }
}
