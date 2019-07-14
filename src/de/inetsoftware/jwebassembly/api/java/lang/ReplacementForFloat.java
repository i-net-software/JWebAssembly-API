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
 * Replacement methods for the class java.lang.Float.
 * 
 * @author Volker Berlin
 */
class ReplacementForFloat {

    /**
     * Replacement for Float.floatToRawIntBits(float)
     */
    @Replace( "java/lang/Float.floatToRawIntBits(F)I" )
    @WasmTextCode( "local.get 0 " //
                    + "i32.reinterpret_f32 " //
                    + "return" )
    static int floatToRawIntBits(float value) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for Float.intBitsToFloat(int)
     */
    @Replace( "java/lang/Float.intBitsToFloat(I)F" )
    @WasmTextCode( "local.get 0 " //
                    + "f32.reinterpret_i32 " //
                    + "return" )
    static float intBitsToFloat(int value) {
        return 0; // for Java compiler
    }
}
