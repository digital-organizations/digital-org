package com.engg.digitalorg.exception;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;

import static org.mockito.MockitoAnnotations.initMocks;

public class BadRequestExceptionTest {

    @Mock
    private Throwable mockCause;

    private BadRequestException badRequestExceptionUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        badRequestExceptionUnderTest = new BadRequestException("message", mockCause, false, false);
    }
}
