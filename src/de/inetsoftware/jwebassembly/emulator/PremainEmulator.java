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

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * Start class of the emulator's javaagent.
 * 
 * @author Volker Berlin
 */
public class PremainEmulator {

    /**
     * Premain method of the javaagent.
     * 
     * @param agentArgs
     *            the arguments that are passed to agent via -javaagent:jwebassembly-api.jar=agentArgs
     * @param inst
     *            services to instrument Javaprogramming language code.
     */
    public static void premain( @SuppressWarnings( "unused" ) @Nullable String agentArgs, Instrumentation inst ) {
        inst.addTransformer( new ClassFileTransformer() {
            @Override
            public byte[] transform( ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer ) throws IllegalClassFormatException {
                try {
                    ClassReader cr = new ClassReader( classfileBuffer );
    
                    // container for find Import annotations
                    Map<String, ImportAnnotation> annotations = new HashMap<>();
                    ImportAnnotationClassVisitor visitor = new ImportAnnotationClassVisitor( className, annotations );
                    cr.accept( visitor, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES );
    
                    // if there Import annotations then patch the code
                    if( !annotations.isEmpty() ) {
                        ClassWriter cw = new ClassWriter( cr, ClassWriter.COMPUTE_FRAMES );
                        ClassVisitor cv = new PatchImportClassVisitor( cw, annotations );
                        cr.accept( cv, 0 );
                        return cw.toByteArray();
                    } else {
                        // all other classes return as original
                        return classfileBuffer;
                    }
                } catch( Throwable th) {
                    th.printStackTrace();
                    throw th;
                }
            }
        } );
    }
}
