package com.engg.digitalorg.services;

import com.engg.digitalorg.api.EmailApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;

@RestController("EmailService")
public class EmailService implements EmailApi {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
//        TODO : https://support.google.com/mail/answer/13273?hl=en&rd=2   Gmail SMTP server.
    }
}
