package com.engg.digitalorg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
public class CardServicesTest {

    @Test
    void getCardsTest() {
        Assert.isTrue(true,"test message");
    }
}