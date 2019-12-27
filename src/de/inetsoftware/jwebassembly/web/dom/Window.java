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

import de.inetsoftware.jwebassembly.web.JSObject;

/**
 * https://developer.mozilla.org/en-US/docs/Web/API/Window
 * 
 * @author Volker Berlin
 */
public abstract class Window extends JSObject {

    /**
     * Create a Java instance as wrapper of the JavaScript object.
     * 
     * @param peer
     *            the native JavaScript object
     */
    Window( Object peer ) {
        super( peer );
    }

    /**
     * https://developer.mozilla.org/en-US/docs/Web/API/Window/document
     * 
     * @return the document
     */
    public static Document document() {
        return new Document( win_get( "document" ) );
    }
}
