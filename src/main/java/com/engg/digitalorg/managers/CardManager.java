package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CardManager {

    @Autowired
    private CardRepository cardRepository;

    public Card getCard(Integer cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        return card.get();
    }

    public Card createCard(Card card) {
//        TODO: build short url with expire,
//        TODO: build short url without expire if group,
//        TODO: generate created by, created date, modified by, modified date
        return cardRepository.save(card);
    }

    public List<Card> getAllCard() {
        return cardRepository.findAll();
    }
}
