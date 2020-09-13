package com.engg.digitalorg.services;

import com.engg.digitalorg.api.EmailValidationApi;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.EmailValidationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("EmailValidationServices")
public class EmailValidationServices implements EmailValidationApi {

    @Autowired
    private EmailValidationManager emailValidationManager;

    @Override
    public ResponseEntity<Map> emailValidation(String emailId) throws DigitalOrgException {
        return new ResponseEntity<>(emailValidationManager.getEmailValidationManager(emailId), HttpStatus.OK);
    }
}
