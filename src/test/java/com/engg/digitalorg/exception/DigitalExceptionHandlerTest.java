package com.engg.digitalorg.exception;

import org.testng.annotations.BeforeMethod;

public class DigitalExceptionHandlerTest {

    private DigitalExceptionHandler digitalExceptionHandlerUnderTest;

    @BeforeMethod
    public void setUp() {
        digitalExceptionHandlerUnderTest = new DigitalExceptionHandler();
    }
}
