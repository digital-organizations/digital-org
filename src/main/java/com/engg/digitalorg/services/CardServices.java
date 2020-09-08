package com.engg.digitalorg.services;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/card")
public class CardServices {

    @GetMapping("/all")
    public String getCards() {
        return "Returning multiple card";
    }

    @GetMapping("/{card-id}")
    public String getCard() {
        return "Returning single card";
    }
}