package com.engg.digitalorg.services;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HomeServices {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Digital Org!";
    }

    @RequestMapping("/home")
    public String home() {
        return "Greetings from Digital Org Home page!";
    }

}