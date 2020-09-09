package com.engg.digitalorg.services;

import com.engg.digitalorg.api.CardApi;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.CardManager;
import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.RequestCard;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("CardService")
public class CardService implements CardApi {

    @Autowired
    private CardManager cardManager;

    @Override
    public Card createCard(RequestCard requestCard) throws DigitalOrgException {
        ModelMapper modelMapper = new ModelMapper();
        Card card = modelMapper.map(requestCard, Card.class);
        return cardManager.createCard(card);
    }

    @Override
    public Card getCard(Integer cardId) throws DigitalOrgException {
        return cardManager.getCard(cardId);
        //TODO : only authorize card display
    }

    @Override
    public List<Card> getAllCard() throws DigitalOrgException {
        return cardManager.getAllCard();
    }
}
