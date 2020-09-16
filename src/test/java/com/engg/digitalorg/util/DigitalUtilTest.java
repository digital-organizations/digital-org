package com.engg.digitalorg.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * The type Digital util test.
 */
public class DigitalUtilTest {

    /**
     * Test is email valid.
     */
    @Test
    public void testIsEmailValid() {
        assertTrue(DigitalUtil.isEmailValid("email"));
    }

    /**
     * Test is url valid.
     */
    @Test
    public void testIsUrlValid() {
        assertTrue(DigitalUtil.isUrlValid("url"));
    }

    /**
     * Test compress bytes.
     */
    @Test
    public void testCompressBytes() {
        assertEquals("content".getBytes(), DigitalUtil.compressBytes("content".getBytes()));
    }

    /**
     * Test decompress bytes.
     */
    @Test
    public void testDecompressBytes() {
        assertEquals("content".getBytes(), DigitalUtil.decompressBytes("content".getBytes()));
    }
}
