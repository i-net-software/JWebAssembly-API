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
 * https://developer.mozilla.org/en-US/docs/Web/API/Node
 * 
 * @author Volker Berlin
 */
public class Node extends EventTarget {

    /** An Element node like &lt;p&gt; or &lt;div&gt; */
    public static final int ELEMENT_NODE                = 1;

    /** An Attribute of an Element. */
    public static final int ATTRIBUTE_NODE              = 2;

    /** The actual Text inside an Element or Attr. */
    public static final int TEXT_NODE                   = 3;

    /** A CDATASection, such as &lt;!CDATA[[ … ]]&lt;. */
    public static final int CDATA_SECTION_NODE          = 4;

    /** A ProcessingInstruction of an XML document, such as &lt;?xml-stylesheet … ?&lt;. */
    public static final int PROCESSING_INSTRUCTION_NODE = 7;

    /** A Comment node, such as &lt;!-- … --&lt;. */
    public static final int COMMENT_NODE                = 8;

    /** A Document node. */
    public static final int DOCUMENT_NODE               = 9;

    /** A DocumentType node, such as &lt;!DOCTYPE html&lt;. */
    public static final int DOCUMENT_TYPE_NODE          = 10;

    /** A DocumentFragment node. */
    public static final int DOCUMENT_FRAGMENT_NODE      = 11;

    /**
     * Create a Java instance as wrapper of the JavaScript object.
     * 
     * @param peer
     *            the native JavaScript object
     */
    Node( Object peer ) {
        super( peer );
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/API/Node/nodeType
     * 
     * @return the node type
     */
    public int nodeType() {
        return get( "nodeType" );
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/API/Node/childNodes
     * 
     * @return list of chield nodes
     */
    public NodeList childNodes() {
        return new NodeList( get( "childNodes" ) );
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/API/Node/appendChild
     * 
     * @param child
     *            the child
     */
    public void appendChild( Node child ) {
        invoke( "appendChild", child.peer );
    }
}
