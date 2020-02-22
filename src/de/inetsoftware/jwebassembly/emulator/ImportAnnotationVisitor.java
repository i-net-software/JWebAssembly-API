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
package de.inetsoftware.jwebassembly.emulator;

import static org.objectweb.asm.Opcodes.ASM7;

import java.util.Map;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Type;

/**
 * Scan the values of an Import annotation
 * 
 * @author Volker Berlin
 */
class ImportAnnotationVisitor extends AnnotationVisitor {

    private ImportAnnotation anno;

    /**
     * Create an instance and add an ImportAnnotation to the container.
     * 
     * @param className
     *            the name of the class in the internal form of fully qualified class. For example, "java/util/List".
     * @param methodName
     *            method name
     * @param descriptor
     *            the method's descriptor (see {@link Type}).
     * @param annotations
     *            container for found annotations, key is method name with signature
     */
    public ImportAnnotationVisitor( String className, String methodName, String descriptor, Map<String, ImportAnnotation> annotations ) {
        super( ASM7 );
        annotations.put( methodName + descriptor, anno = new ImportAnnotation() );
        // default values for module and name
        anno.module = className.substring( className.lastIndexOf( '/' ) + 1 );
        anno.name = methodName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit( String name, Object value ) {
        switch( name ) {
            case "module":
                anno.module = (String)value;
                break;
            case "name":
                anno.name = (String)value;
                break;
            case "js":
                anno.javaScript = (String)value;
                break;
        }
    }
}
