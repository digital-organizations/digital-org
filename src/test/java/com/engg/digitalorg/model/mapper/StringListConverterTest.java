package com.engg.digitalorg.model.mapper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class StringListConverterTest {

    private StringListConverter stringListConverterUnderTest;

    @BeforeMethod
    public void setUp() {
        stringListConverterUnderTest = new StringListConverter();
    }

    @Test
    public void testConvertToDatabaseColumn() {
        // Setup
        final List<String> stringList = Arrays.asList("value");

        // Run the test
        final String result = stringListConverterUnderTest.convertToDatabaseColumn(stringList);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testConvertToEntityAttribute() {
        // Setup
        final List<String> expectedResult = Arrays.asList("value");

        // Run the test
        final List<String> result = stringListConverterUnderTest.convertToEntityAttribute("string");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
