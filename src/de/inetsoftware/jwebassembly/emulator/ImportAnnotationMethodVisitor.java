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

import java.util.Map;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import de.inetsoftware.jwebassembly.api.annotation.Import;

/**
 * Search for methods with the Import annotation of JWebAssembly.
 * 
 * @author Volker Berlin
 */
class ImportAnnotationMethodVisitor extends MethodVisitor {

    private static final String           IMPORT_ANN = Type.getDescriptor( Import.class );

    private String                        className;

    private String                        methodName;

    private String                        descriptor;

    private Map<String, ImportAnnotation> annotations;

    /**
     * Create an instance.
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
    ImportAnnotationMethodVisitor( String className, String methodName, String descriptor, Map<String, ImportAnnotation> annotations ) {
        super( Opcodes.ASM7 );
        this.className = className;
        this.methodName = methodName;
        this.descriptor = descriptor;
        this.annotations = annotations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AnnotationVisitor visitAnnotation( String descriptor, boolean visible ) {
        if( IMPORT_ANN.equals( descriptor ) ) {
            return new ImportAnnotationVisitor( className, methodName, this.descriptor, annotations );
        }
        return null;
    }

}
