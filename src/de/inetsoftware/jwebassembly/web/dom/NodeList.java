/*
 * Copyright 2021 Volker Berlin (i-net software)
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

import de.inetsoftware.jwebassembly.api.annotation.Import;
import de.inetsoftware.jwebassembly.web.DOMString;
import de.inetsoftware.jwebassembly.web.JSObject;

/**
 * https://developer.mozilla.org/en-US/docs/Web/API/NodeList
 * 
 * @author Volker Berlin
 */
public class NodeList extends JSObject {

    /**
     * Create a Java instance as wrapper of the JavaScript object.
     * 
     * @param peer
     *            the native JavaScript object
     */
    NodeList( Object peer ) {
        super( peer );
    }

    /**
     * The number of items.
     * 
     * @return the count
     */
    public int length() {
        return get( "length" );
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/API/NodeList/item
     * 
     * @param idx
     *            the index
     * @return the item
     */
    public Node item( int idx ) {
        Object peer = invoke( "item", idx );
        HTMLElement node = new HTMLElement( peer );
        switch( node.nodeType() ) {
            case Node.ELEMENT_NODE:
                String tagName = node.tagName();
                return HTMLElement.createWrapper( tagName, peer );
            case Node.TEXT_NODE:
                return new Text( peer );
        }
        return new Node( peer );
    }
}
