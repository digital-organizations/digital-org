package com.engg.digitalorg.util;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * The type Base conversion test.
 */
public class BaseConversionTest {

    private BaseConversion baseConversionUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        baseConversionUnderTest = new BaseConversion();
    }

    /**
     * Test encode.
     */
    @Test
    public void testEncode() {
        // Setup

        // Run the test
        final String result = baseConversionUnderTest.encode(0L);

        // Verify the results
        assertEquals("result", result);
    }

    /**
     * Test decode.
     */
    @Test
    public void testDecode() {
        // Setup

        // Run the test
        final long result = baseConversionUnderTest.decode("input");

        // Verify the results
        assertEquals(0L, result);
    }
}
