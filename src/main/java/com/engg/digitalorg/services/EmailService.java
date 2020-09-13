package com.engg.digitalorg.services;

import com.engg.digitalorg.api.CardApi;
import com.engg.digitalorg.api.EmailApi;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.CardManager;
import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.request.CardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.util.List;

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
