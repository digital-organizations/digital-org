package com.engg.digitalorg.util;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DigitalUtilTest {

    @Test
    public void testIsEmailValid() {
        assertTrue(DigitalUtil.isEmailValid("email"));
    }

    @Test
    public void testIsUrlValid() {
        assertTrue(DigitalUtil.isUrlValid("url"));
    }

    @Test
    public void testCompressBytes() {
        assertEquals("content".getBytes(), DigitalUtil.compressBytes("content".getBytes()));
    }

    @Test
    public void testDecompressBytes() {
        assertEquals("content".getBytes(), DigitalUtil.decompressBytes("content".getBytes()));
    }
}
