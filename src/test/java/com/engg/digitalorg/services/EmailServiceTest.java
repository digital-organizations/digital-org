package com.engg.digitalorg.services;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmailServiceTest {

    @Mock
    private JavaMailSender mockEmailSender;

    @InjectMocks
    private EmailService emailServiceUnderTest;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testSendSimpleMessage() {
        // Setup

        // Run the test
        emailServiceUnderTest.sendSimpleMessage("to", "subject", "text");

        // Verify the results
        verify(mockEmailSender).send(new SimpleMailMessage());
    }

    @Test
    public void testSendSimpleMessage_JavaMailSenderThrowsMailException() {
        // Setup
        doThrow(MailException.class).when(mockEmailSender).send(new SimpleMailMessage());

        // Run the test
        emailServiceUnderTest.sendSimpleMessage("to", "subject", "text");

        // Verify the results
    }
}
