package com.engg.digitalorg.services;

import com.engg.digitalorg.managers.UrlManager;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * The type Url service test.
 */
public class UrlServiceTest {

    @Mock
    private UrlManager mockUrlManager;
    @Mock
    private JavaMailSender mockEmailSender;

    @InjectMocks
    private UrlService urlServiceUnderTest;

    /**
     * Sets up.
     */
    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    /**
     * Test get and redirect.
     */
    @Test
    public void testGetAndRedirect() {
        // Setup
        when(mockUrlManager.getOriginalUrl("shortUrl")).thenReturn("result");

        // Run the test
        final ResponseEntity<Void> result = urlServiceUnderTest.getAndRedirect("shortUrl");

        // Verify the results
    }

    /**
     * Test send simple message.
     */
    @Test
    public void testSendSimpleMessage() {
        // Setup

        // Run the test
        urlServiceUnderTest.sendSimpleMessage("to", "subject", "text");

        // Verify the results
        verify(mockEmailSender).send(new SimpleMailMessage());
    }

    /**
     * Test send simple message java mail sender throws mail exception.
     */
    @Test
    public void testSendSimpleMessage_JavaMailSenderThrowsMailException() {
        // Setup
        doThrow(MailException.class).when(mockEmailSender).send(new SimpleMailMessage());

        // Run the test
        urlServiceUnderTest.sendSimpleMessage("to", "subject", "text");

        // Verify the results
    }
}
