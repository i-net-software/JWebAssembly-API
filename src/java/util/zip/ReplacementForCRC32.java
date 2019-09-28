/*
 * Copyright 2019 Volker Berlin (i-net software)
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
package java.util.zip;

import de.inetsoftware.jwebassembly.api.annotation.Replace;

/**
 * Replacement methods for the class java.util.zip.CRC32
 * 
 * @author Volker Berlin
 */
class ReplacementForCRC32 {

    /**
     * Replacement for CRC32.update(int, int)
     */
    @Replace( "java/util/zip/CRC32.update(II)I" )
    static int update( int crc, int b ) {
        return 0; // for Java compiler
    }

    /**
     * Replacement for CRC32.updateBytes(int, byte[], int, int)
     */
    @Replace( "java/util/zip/CRC32.updateBytes(I[BII)I" )
    static int updateBytes( int crc, byte[] data, int off, int len ) {
        int[] crcTable = cachedCrcTable;
        if( crcTable == null ) {
            crcTable = makeCRCTable();
        }

        crc ^= -1;
        for( ; off < len; off++ ) {
            crc = (crc >>> 8) ^ crcTable[(crc ^ data[off]) & 0xFF];
        }

        return crc ^ -1;
    }

    /**
     * Create table for a byte-wise 32-bit CRC calculation on the polynomial:
     * x^32+x^26+x^23+x^22+x^16+x^12+x^11+x^10+x^8+x^7+x^5+x^4+x^2+x+1.
     * 
     * @return the table
     */
    private static int[] makeCRCTable() {
        int[] crcTable = cachedCrcTable;
        if( crcTable != null ) {
            return crcTable;
        }
        crcTable = new int[256];
        for( int n = 0; n < 256; n++ ) {
            int c = n;
            for( int k = 0; k < 8; k++ ) {
                c = ((c & 1) == 1 ? (0xEDB88320 ^ (c >>> 1)) : (c >>> 1));
            }
            crcTable[n] = c;
        }
        return cachedCrcTable = crcTable;
    }

    private static int[] cachedCrcTable;
}
