package com.engg.digitalorg;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * The type Swagger config test.
 */
public class SwaggerConfigTest {

    private SwaggerConfig swaggerConfigUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        swaggerConfigUnderTest = new SwaggerConfig();
    }

    /**
     * Test api.
     */
    @Test
    public void testApi() {
        // Setup

        // Run the test
        final Docket result = swaggerConfigUnderTest.api();

        // Verify the results
    }
}
