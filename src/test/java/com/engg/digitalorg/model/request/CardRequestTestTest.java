package com.engg.digitalorg.model.request;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CardRequestTestTest {

    private CardRequestTest cardRequestTestUnderTest;

    @BeforeMethod
    public void setUp() {
        cardRequestTestUnderTest = new CardRequestTest();
    }

    @Test
    public void testSetUp() {
        // Setup

        // Run the test
        cardRequestTestUnderTest.setUp();

        // Verify the results
    }
}
