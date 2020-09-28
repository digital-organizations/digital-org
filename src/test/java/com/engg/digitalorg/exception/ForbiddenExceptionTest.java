package com.engg.digitalorg.exception;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;

import static org.mockito.MockitoAnnotations.initMocks;

public class ForbiddenExceptionTest {

    @Mock
    private Throwable mockCause;

    private ForbiddenException forbiddenExceptionUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        forbiddenExceptionUnderTest = new ForbiddenException("message", mockCause, false, false);
    }
}
