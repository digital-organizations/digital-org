package com.engg.digitalorg.api;

import com.engg.digitalorg.managers.CardManager;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.repository.CardRepository;
import com.engg.digitalorg.services.CardService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

public class CardApiTest {

    @InjectMocks
    private CardService cardService;

    @Mock
    private CardManager cardManager;

    @Mock
    private CardRepository cardRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCardsTest() throws Exception {
        List<CardResponse> cardList = new ArrayList<>();
        cardList.add(new CardResponse());
        Mockito.when(cardService.getAllCard()).thenReturn(cardList);
        List<CardResponse> cards = cardManager.getAllCard();
        Mockito.verify(cardManager).getAllCard();
        Assert.assertEquals(cards, cardList);

    }
}
