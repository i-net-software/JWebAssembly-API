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
    }

    @Test
    public void area() {
        Document document = Window.document();
        HTMLElement div = document.createElement( "area" );
        assertTrue( div instanceof HTMLAreaElement );
    }
}
