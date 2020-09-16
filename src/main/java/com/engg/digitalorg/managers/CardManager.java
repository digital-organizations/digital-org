package com.engg.digitalorg.managers;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.Card;
import com.engg.digitalorg.model.entity.Icon;
import com.engg.digitalorg.model.entity.Url;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.request.CardUpdateRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.repository.CardRepository;
import com.engg.digitalorg.repository.GroupCustomRepository;
import com.engg.digitalorg.repository.IconRepository;
import com.engg.digitalorg.repository.UrlRepository;
import com.engg.digitalorg.util.BaseConversion;
import com.engg.digitalorg.util.DigitalUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CardManager {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedString.toCharArray();
    private int base = allowedCharacters.length;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    IconRepository iconRepository;

    @Autowired
    BaseConversion baseConversion;

    @Autowired
    GroupCustomRepository groupCustomRepository;

    public Url createShortUrl(String originalUrl, Date date, int cardId) {
        Url url = new Url();
        url.setLong_url(originalUrl);
        url.setExpires_date(date);
        url.setCreated_date(new Date());
        url.setCard_id(cardId);
        url.setShort_url(baseConversion.encode(cardId));
        Url newUrl = urlRepository.save(url);
        return newUrl;
    }


    public CardResponse createCard(CardRequest cardRequest)  throws IOException {
        cardRequestValidation(cardRequest);
        ModelMapper reqModelMapper = new ModelMapper();
        Card card = reqModelMapper.map(cardRequest, Card.class);
        card.setCreated_date(new Date());
        card.setUpdated_date(new Date());
        card.setActive(true);

        Card response = cardRepository.save(card);

        ClassPathResource classPathResource = new ClassPathResource("img/favicon.png");
        byte[] bytes = new byte[(int) classPathResource.contentLength()];
        Icon icon = iconRepository.save(new Icon("favicon.png", "image/png", response.getId(), DigitalUtil.compressBytes(bytes)));


        if(cardRequest.getOriginal_url() != null) {
            Url newUrl = createShortUrl(cardRequest.getOriginal_url(), cardRequest.getExpire_date(), response.getId());
            cardRepository.updateAddress(newUrl.getId(), icon.getId(),response.getId());
        }

        ModelMapper resModelMapper = new ModelMapper();
        CardResponse cardResponse = resModelMapper.map(response, CardResponse.class);
        cardResponse.setHasAdmin(true);
        return cardResponse;
    }

    public void cardRequestValidation(CardRequest cardRequest) {
        if(!DigitalUtil.isEmailValid(cardRequest.getCreated_by()) || !DigitalUtil.isEmailValid(cardRequest.getUpdated_by())) {
            throw new DigitalOrgException("Email is not valid");
        }
        if(!DigitalUtil.isUrlValid(cardRequest.getOriginal_url())) {
            throw new DigitalOrgException("Original Url is not valid");
        }
    }


/*
    public String convertToShortUrl(UrlLongRequest request) {
        var url = new URL();
        url.setLongUrl(request.getLongUrl());
        url.setExpiresDate(request.getExpiresDate());
        url.setCreatedDate(new Date());
        var entity = urlRepository.save(url);

        return conversion.encode(entity.getId());
    }
    */

    public Card getCardById(int cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        return card.get();
    }

    public void uplaodImage(Icon icon) {
        iconRepository.save(icon);
    }

    public Icon downloadImage(int cardId) {
        Optional<Icon> icon = iconRepository.findById(getCardById(cardId).getIcon_id());
        return icon.get();
    }

    public void deleteCard(int cardId) {
        groupCustomRepository.removeCardFromGroup(cardId);
        cardRepository.deleteById(cardId);
    }


    public List<CardResponse> getAllCard(String emailId) {
        List<CardResponse> cardList = cardRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, CardResponse.class), Collectors.toList()));

        List<CardResponse> cardResponseList = cardList.stream().map(cardResponse -> {
            if(cardResponse.getCreated_by().equals(emailId)) {
                cardResponse.setHasAdmin(true);
            }
            else {
                cardResponse.setHasAdmin(false);
            }
            return cardResponse;
        }).collect(Collectors.toList());
        return cardResponseList;
    }

    public Object updateCard(CardUpdateRequest cardRequest) {
        CardResponse cardResponse = null;
        Optional<Card> cardOptional = cardRepository.findById(cardRequest.getId());
        Card card = cardOptional.get();
        if(card !=null && card.getCreated_by().equals(cardRequest.getUpdated_by())) {
            ModelMapper reqModelMapper = new ModelMapper();
            Card cardMapper = reqModelMapper.map(cardRequest, Card.class);
            cardMapper.setUpdated_date(new Date());

            if(cardRequest.getOriginal_url() != null) {
                updateShortUrl(card, cardRequest.getOriginal_url(), cardRequest.getExpire_date(), card.getId());
            }
            cardRepository.save(cardMapper);
            ModelMapper resModelMapper = new ModelMapper();
            cardResponse = resModelMapper.map(cardMapper, CardResponse.class);
            cardResponse.setHasAdmin(true);
            return cardResponse;
        }
        return cardResponse;
    }

    public void updateShortUrl(Card card, String originalUrl, Date date, int urlId) {
        urlRepository.updateUrl(originalUrl, baseConversion.encode(card.getId()), date, urlId);
    }
}
