package com.engg.digitalorg.exception;

import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;

import static org.mockito.MockitoAnnotations.initMocks;

public class DigitalOrgExceptionTest {

    @Mock
    private Throwable mockCause;

    private DigitalOrgException digitalOrgExceptionUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        digitalOrgExceptionUnderTest = new DigitalOrgException("message", mockCause, false, false);
    }
}
