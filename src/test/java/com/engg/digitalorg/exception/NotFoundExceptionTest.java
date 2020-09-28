package com.engg.digitalorg.exception;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;

import static org.mockito.MockitoAnnotations.initMocks;

public class NotFoundExceptionTest {

    @Mock
    private Throwable mockCause;

    private NotFoundException notFoundExceptionUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        notFoundExceptionUnderTest = new NotFoundException("message", mockCause, false, false);
    }
}
