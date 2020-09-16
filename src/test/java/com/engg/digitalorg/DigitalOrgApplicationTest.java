package com.engg.digitalorg;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The type Digital org application test.
 */
public class DigitalOrgApplicationTest {

    private DigitalOrgApplication digitalOrgApplicationUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        digitalOrgApplicationUnderTest = new DigitalOrgApplication();
    }

    /**
     * Test multipart resolver.
     */
    @Test
    public void testMultipartResolver() {
        // Setup

        // Run the test
        final CommonsMultipartResolver result = digitalOrgApplicationUnderTest.multipartResolver();

        // Verify the results
    }

    /**
     * Test main.
     */
    @Test
    public void testMain() {
        // Setup

        // Run the test
        DigitalOrgApplication.main(new String[]{"value"});

        // Verify the results
    }
}
