package com.engg.digitalorg.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServices {

    @RequestMapping("/user")
    public String index() {
        return "Greetings from Digital Org!";
    }

}