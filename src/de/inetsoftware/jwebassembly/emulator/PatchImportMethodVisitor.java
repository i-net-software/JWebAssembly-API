/*
 * Copyright 2020 - 2021 Volker Berlin (i-net software)
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

import static org.objectweb.asm.Opcodes.AASTORE;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ANEWARRAY;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.DRETURN;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.FRETURN;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.LRETURN;
import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.RETURN;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * Create the new proxy code for a native method with Import annotation.
 * 
 * @author Volker Berlin
 */
class PatchImportMethodVisitor extends MethodVisitor {

    private static final String RUNNER_CLASS = Type.getInternalName( JWebAssemblyEmulator.JavaFxApplication.class );

    private static final String RUNNER_FUNC  = "executeScript";

    private static final String RUNNER_DESC  =
                    Type.getMethodDescriptor( Type.getType( Object.class ), Type.getType( String.class ), Type.getType( String.class ), Type.getType( Object[].class ) );

    private final String        desc;

    private ImportAnnotation    anno;

    /**
     * Create an instance.
     * 
     * @param mw
     *            the method visitor of the ClassWriter
     * @param desc
     *            the signature of the method (parameters and return)
     * @param anno
     *            the values of the Import annotation
     */
    PatchImportMethodVisitor( MethodVisitor mw, String desc, ImportAnnotation anno ) {
        super( Opcodes.ASM7, mw );
        this.desc = desc;
        this.anno = anno;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitEnd() {
        JWebAssemblyEmulator.JavaFxApplication.registerScript( anno );
        visitLdcInsn( anno.module );
        visitLdcInsn( anno.name );

        Type methodType = Type.getType( desc );
        Type[] args = methodType.getArgumentTypes();

        // allocate an array for the method parameters 
        visitIntInsn( BIPUSH, args.length ); // count of arguments
        visitTypeInsn( ANEWARRAY, Type.getInternalName( Object.class ) );

        // assign the method parameters to the array
        for( int i = 0; i < args.length; i++ ) {
            visitInsn( DUP ); // duplicate the reference to the array on the stack
            visitIntInsn( BIPUSH, i ); // array index
            Type arg = args[i];
            int opcode;
            switch( arg.getSort() ) {
                //TODO other types
                default:
                    opcode = ALOAD;
            }
            visitVarInsn( opcode, i );
            visitInsn( AASTORE );
        }

        // call the script
        visitMethodInsn( INVOKESTATIC, RUNNER_CLASS, RUNNER_FUNC, RUNNER_DESC, false );

        // return the value
        switch( methodType.getReturnType().getSort() ) {
            case Type.VOID:
                visitInsn( POP );
                visitInsn( RETURN );
                break;
            case Type.INT:
            case Type.SHORT:
            case Type.BYTE:
            case Type.CHAR:
                visitInsn( IRETURN );
                break;
            case Type.LONG:
                visitInsn( LRETURN );
                break;
            case Type.FLOAT:
                visitInsn( FRETURN );
                break;
            case Type.DOUBLE:
                visitInsn( DRETURN );
                break;
            default:
                visitInsn( ARETURN );
        }

        // finish
        visitMaxs( 0, 0 );
        super.visitEnd();
    }

}
