/*
 * Copyright 2020 Volker Berlin (i-net software)
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
import de.inetsoftware.jwebassembly.api.annotation.Partial;
import de.inetsoftware.jwebassembly.api.annotation.Replace;
import de.inetsoftware.jwebassembly.web.DOMString;

/**
 * Additional methods for the class java.lang.String.
 * 
 * @author Volker Berlin
 */
@Partial( "java/lang/String" )
class ReplacementForString {

    /**
     * hold the DOMString if there is already any
     */
    private DOMString domStr;

    /**
     * Create a DOMString via JavaScript from char array.
     * 
     * @param value
     *            the characters of the string
     * @return the DOMString
     */
    @Import( module = "Web", name = "fromChars", js = "(v)=>{" + //
                    "v=v[2];" + //
                    "var s='';" + //
                    "for(var i=0;i<v.length;i++){" + //
                    "s+=String.fromCharCode(v[i]);" + //
                    "}" + //
                    "return s}" )
    private static native DOMString fromChars( char[] value );

    /**
     * Getter and factory for DOMStrings.
     * @return the string
     */
    @Replace( "de/inetsoftware/jwebassembly/web/JSObject.domString(Ljava/lang/String;)Lde/inetsoftware/jwebassembly/web/DOMString;" )
    private Object domString() {
        DOMString domStr = this.domStr; 
        if( domStr == null ) {
            //TODO toCharArray() create a copy which we not need, but it work with Java 8 and 11. A better solution can be a multi release jar file.
            domStr = fromChars( toCharArray() );
            this.domStr = domStr;
        }
        return domStr;
    }

    /**
     * Placeholder for existing public method.
     */
    native public char[] toCharArray();
}
