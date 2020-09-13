package com.engg.digitalorg.model.mapper;

import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.request.CardResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CardMapper {

    CardResponse toCardResponse(Card card);

    List<CardResponse> toCardResponses(List<Card> cards);

    Card toCard(CardRequest cardRequest);

}
