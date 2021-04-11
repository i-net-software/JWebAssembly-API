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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.inetsoftware.jwebassembly.emulator.JWebAssemblyRunner;

@RunWith( JWebAssemblyRunner.class )
public class DomTest {

    @Test
    public void div() {
        Document document = Window.document();
        HTMLElement div = document.createElement( "div" );
        assertTrue( div instanceof HTMLDivElement );
        assertEquals( "DIV", div.tagName() );
        assertEquals( Node.ELEMENT_NODE, div.nodeType() );
    }

    @Test
    public void area() {
        Document document = Window.document();
        HTMLElement div = document.createElement( "area" );
        assertTrue( div instanceof HTMLAreaElement );
        assertEquals( "AREA", div.tagName() );
        assertEquals( Node.ELEMENT_NODE, div.nodeType() );
    }

    @Test
    public void nodeList() {
        Document document = Window.document();
        HTMLElement div = document.createElement( "div" );
        NodeList childNodes = div.childNodes();
        assertEquals( 0, childNodes.length() );

        Text text = document.createTextNode( "some text" );
        assertEquals( Node.TEXT_NODE, text.nodeType() );
        div.appendChild( text );
        assertEquals( 1, childNodes.length() );
        Node item = childNodes.item( 0 );
        assertEquals( Node.TEXT_NODE, item.nodeType() );
        assertTrue( item instanceof Text );
    }
}
