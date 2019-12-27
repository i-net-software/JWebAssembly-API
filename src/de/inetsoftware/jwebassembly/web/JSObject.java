/*
 * Copyright 2019 - 2020 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.web;

import de.inetsoftware.jwebassembly.api.annotation.Import;

/**
 * The base type for all wrapped JavaScript objects.
 * 
 * @author Volker Berlin
 */
public class JSObject {

    /** module name for JavaScript helper of the web */
    private final static String WEB = "Web";

    /**
     * Native object from JavaScript.
     */
    protected final Object      peer;

    /**
     * Create a Java instance as wrapper of the JavaScript object.
     * 
     * @param peer
     *            the native JavaScript object
     */
    protected JSObject( Object peer ) {
        this.peer = peer;
    }

    /**
     * Native get a JavaScript property value by name from global variable window.
     * 
     * @param <T>
     *            the return type
     * @param propName
     *            the name of the property as DOMString
     * @return the value of the property
     */
    @Import( module = WEB, js = "(p)=>window[p]" )
    protected static native <T> T win_get( String propName );


    /**
     * Native get a JavaScript property value by name.
     * 
     * @param <T>
     *            the return type
     * @param peer
     *            the JavaScript object
     * @param propName
     *            the name of the property as DOMString
     * @return the value of the property
     */
    @Import( module = WEB, js = "(o,p)=>o[p]" )
    private static native <T> T get0( Object peer, String propName );

    /**
     * Get the value of a property of this object.
     * 
     * @param <T>
     *            the return type
     * @param propName
     *            the name of the property
     * @return the value of the property
     */
    protected <T> T get( String propName ) {
        return get0( peer, propName );
    }

    /**
     * Native invoke a JavaScript method with one parameter.
     * 
     * @param <T>
     *            the return type
     * @param peer
     *            the JavaScript object
     * @param methodName
     *            the method name
     * @param param1
     *            the parameter
     * @return the return value
     */
    @Import( module = WEB, js = "(o,m,p1)=>o[m](p1)" )
    private static native <T> T invoke1( Object peer, String methodName, Object param1 );

    /**
     * Invoke a JavaScript method with one parameter of this object.
     * 
     * @param <T>
     *            the return type
     * @param methodName
     *            the method name
     * @param param1
     *            the parameter
     * @return the return value
     */
    protected <T> T invoke( String methodName, Object param1 ) {
        return invoke1( peer, methodName, param1 );
    }
}
