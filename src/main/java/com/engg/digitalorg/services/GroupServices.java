package com.engg.digitalorg.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupServices {

    @RequestMapping("/group")
    public String index() {
        return "Greetings from Digital Org!";
    }

}