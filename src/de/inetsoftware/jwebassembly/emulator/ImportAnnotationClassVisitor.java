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

import static org.objectweb.asm.Opcodes.ACC_NATIVE;
import static org.objectweb.asm.Opcodes.ASM7;

import java.util.Map;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * Scan a class for native methods with Import annotations.
 * 
 * @author Volker Berlin
 */
class ImportAnnotationClassVisitor extends ClassVisitor {

    private String                        className;

    private Map<String, ImportAnnotation> annotations;

    /**
     * Create an instance.
     * 
     * @param className
     *            the name of the class in the internal form of fully qualified class. For example, "java/util/List".
     * @param annotations
     *            container for found annotations, key is method name with signature
     */
    ImportAnnotationClassVisitor( String className, Map<String, ImportAnnotation> annotations ) {
        super( ASM7 );
        this.className = className;
        this.annotations = annotations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MethodVisitor visitMethod( int access, String name, String descriptor, String signature, String[] exceptions ) {
        if( (access & ACC_NATIVE) > 0 ) {
            // start scanning of annotations if it is a native method
            return new ImportAnnotationMethodVisitor( className, name, descriptor, annotations );
        }

        // skip all other methods
        return null;
    }
}
