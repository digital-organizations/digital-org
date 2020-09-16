package com.engg.digitalorg.services;

import com.engg.digitalorg.api.UrlApi;
import com.engg.digitalorg.managers.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * The type Url service.
 */
@RestController("UrlService")
public class UrlService implements UrlApi {

    @Autowired
    private UrlManager urlManager;


    @Autowired
    private JavaMailSender emailSender;

    @Override
    public ResponseEntity<Void> getAndRedirect(String shortUrl) {
        String url = urlManager.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }


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
