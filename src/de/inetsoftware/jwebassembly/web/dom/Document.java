/*
 * Copyright 2019 - 2021 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.web.dom;

/**
 * https://developer.mozilla.org/en-US/docs/Web/API/Document
 * 
 * @author Volker Berlin
 */
public class Document extends Node {

    /**
     * Create a Java instance as wrapper of the JavaScript object.
     * 
     * @param peer
     *            the native JavaScript object
     */
    Document( Object peer ) {
        super( peer );
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/API/Document/createElement
     * 
     * @param <T>
     *            the return type
     * @param tagName
     *            type of element
     * @return The new Element
     */
    public <T extends HTMLElement> T createElement( String tagName ) {
        return HTMLElement.createWrapper( tagName, invoke( "createElement", tagName ) );
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/API/Document/createTextNode
     * 
     * @param data
     *            the text data
     * @return the text node
     */
    public Text createTextNode( String data ) {
        return new Text( invoke( "createTextNode", data ) );
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/API/Document/body
     * 
     * @return the body
     */
    public Node body() {
        Object obj = get( "body" );
        if( obj == null ) {
            return null;
        }
        return new Node( obj );
    }
}
