/*
 * Copyright 2022 - 2023 Volker Berlin (i-net software)
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
package de.inetsoftware.jwebassembly.api.java.util;

import java.util.Collections;
import java.util.Iterator;

import de.inetsoftware.jwebassembly.api.annotation.Partial;
import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.util.ServiceLoader.
 * 
 * @author Volker Berlin
 */
@Partial( "java/util/ServiceLoader" )
class ReplacementForServiceLoader<S> implements Iterable<S> {

    /**
     * Replacement for {@link ServiceLoader.iteratorFor(ClassLoader)}
     */
    @Replace( "java/util/ServiceLoader$ModuleServicesLookupIterator.iteratorFor(Ljava/lang/ClassLoader;)Ljava/util/Iterator;" )
    private Iterator iteratorFor( ClassLoader loader ) {
        return Collections.emptyIterator();
    }

    /**
     * Replacement for {@link ServiceLoader.iterator()}
     */
    public Iterator iterator() {
        return Collections.emptyIterator();
    }

    /**
     * Replacement for {@link ServiceLoader.load(Class,ClassLoader)}
     */
    static <S> ReplacementForServiceLoader<S> load( Class<S> service, ClassLoader loader ) {
        return new ReplacementForServiceLoader();
    }

    /**
     * Replacement for {@link ServiceLoader.load(Class)}
     */
    static <S> ReplacementForServiceLoader<S> load( Class<S> service ) {
        return new ReplacementForServiceLoader();
    }

    /**
     * Replacement for {@link ServiceLoader.loadInstalled(Class)}
     */
    static <S> ReplacementForServiceLoader<S> loadInstalled( Class<S> service ) {
        return new ReplacementForServiceLoader();
    }
}
