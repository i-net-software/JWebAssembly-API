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
package de.inetsoftware.jwebassembly.web.dom;

/**
 * https://developer.mozilla.org/en-US/docs/Web/API/HTMLElement
 * 
 * @author Volker Berlin
 */
public class HTMLElement extends Element {

    /**
     * Create a Java instance as wrapper of the DOM object.
     * 
     * @param peer
     *            the native DOM object
     */
    HTMLElement( Object peer ) {
        super( peer );
    }

    /**
     * Create a wrapper for a HTML peer element.
     * 
     * @param tagName
     *            the tag name of the element
     * @param peer
     *            the native DOM object
     * @return the wrapper
     */
    static HTMLElement createWrapper( String tagName, Object peer ) {
        switch( tagName ) {
            case "a":
                return new HTMLAnchorElement( peer );
            case "area":
                return new HTMLAreaElement( peer );
            case "canvas":
                return new HTMLCanvasElement( peer );
            case "div":
                return new HTMLDivElement( peer );
            default:
                return new HTMLElement( peer );
        }
    }
}
