package com.engg.digitalorg.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserServices {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        return "Greetings from Digital Org!";
    }

}