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
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

/**
 * Patch all methods with Import Annotations.
 * 
 * @author Volker Berlin
 */
class PatchImportClassVisitor extends ClassVisitor {

    private Map<String, ImportAnnotation> annotations;

    /**
     * Create a new instance.
     * 
     * @param cw
     *            the class writer
     * @param annotations
     *            methods to patch, key is method name with signature
     */
    PatchImportClassVisitor( ClassWriter cw, Map<String, ImportAnnotation> annotations ) {
        super( ASM7, cw );
        this.annotations = annotations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MethodVisitor visitMethod( int access, String name, String desc, String signature, String[] exceptions ) {
        ImportAnnotation anno = annotations.get( name + desc );
        MethodVisitor mv;

        if( anno != null ) {
            mv = super.visitMethod( access & ~ACC_NATIVE, name, desc, signature, exceptions );
            mv = new PatchImportMethodVisitor( mv, desc, anno );
        } else {
            // use the original method without changes
            mv = super.visitMethod( access, name, desc, signature, exceptions );
        }

        return mv;
    }
}
