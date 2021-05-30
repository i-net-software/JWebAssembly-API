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
package de.inetsoftware.jwebassembly.api.java.lang;

import de.inetsoftware.jwebassembly.api.annotation.Import;
import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.lang.System.
 * 
 * @author Volker Berlin
 */
class ReplacementForSystem {

    /**
     * Replacement for System.registerNatives()
     */
    @Replace( "java/lang/System.registerNatives()V" )
    private static void registerNatives() {
        // nothing
    }

    /**
     * Replacement for System.currentTimeMillis()
     */
    @Replace( "java/lang/System.currentTimeMillis()J" )
    @Import( module = "System", name = "currentTimeMillis", js = "()=>BigInt(Date.now())")
    static native long currentTimeMillis();

    /**
     * Replacement for System.nanoTime()
     */
    @Replace( "java/lang/System.nanoTime()J" )
    @Import( module = "System", name = "nanoTime", js = "()=>BigInt(Date.now()*1000000)")
    static native long nanoTime();

    /**
     * Replacement for {@link System#arraycopy(Object, int, Object, int, int)}
     */
    @Import( js = "(src,srcPos,dest,destPos,length)=>{" + //
                    "src=src[2];" + //
                    "dest=dest[2];" + //
                    "if(destPos<srcPos){" + //
                    "for (var i=0;i<length;i++)dest[i+destPos]=src[i+srcPos];" + //
                    "}else{" + //
                    "for (var i=length-1;i>=0;i--)dest[i+destPos]=src[i+srcPos];" + //
                    "}" + //
                    "}" )
    @Replace( "java/lang/System.arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V" )
    static native void arraycopy();

    /**
     * Replacement for {@link System#identityHashCode(Object)}
     */
    @Import( module = "NonGC", name = "identityHashCode", js = "(o)=>{" //
                    + "var h=o[1];" // 
                    + "while(h==0){" //
                    + "o[1]=h=Math.round((Math.random()-0.5)*0xffff);" //
                    + "}" // 
                    + "return h}" )
    @Replace( "java/lang/System.identityHashCode(Ljava/lang/Object;)I" )
    static native int identityHashCode(Object x);

    /**
     * Replacement for {@link System#exit(int)}
     */
    @Replace( "java/lang/System.exit(I)V" )
    public static void exit(int status) {
        // nothing
    }
}
