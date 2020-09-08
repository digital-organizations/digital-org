package com.engg.digitalorg.services;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupServices {

    @GetMapping("/all")
    public String getCards() {
        return "Returning multiple group";
    }

    @GetMapping("/{group-id}")
    public String getCard() {
        return "Returning single group";
    }
}

