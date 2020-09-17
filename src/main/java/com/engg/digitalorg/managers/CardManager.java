package com.engg.digitalorg.managers;

import com.engg.digitalorg.exception.BadRequestException;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.Card;
import com.engg.digitalorg.model.entity.Icon;
import com.engg.digitalorg.model.entity.SuggestionQueue;
import com.engg.digitalorg.model.entity.Url;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.request.CardUpdateRequest;
import com.engg.digitalorg.model.request.SuggestionQueueRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.repository.*;
import com.engg.digitalorg.util.BaseConversion;
import com.engg.digitalorg.util.DigitalUtil;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Card manager.
 */
@Component
public class CardManager {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private CardInGroupRepository cardInGroupRepository;

    /**
     * The Icon repository.
     */
    @Autowired
    IconRepository iconRepository;

    /**
     * The Base conversion.
     */
    @Autowired
    BaseConversion baseConversion;

    /**
     * The Group custom repository.
     */
    @Autowired
    GroupCustomRepository groupCustomRepository;

    /**
     * The Suggestion queue repository.
     */
    @Autowired
    SuggestionQueueRepository suggestionQueueRepository;

    /**
     * Create short url url.
     *
     * @param originalUrl the original url
     * @param date        the date
     * @param cardId      the card id
     * @return the url
     */
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


    /**
     * Create card card response.
     *
     * @param cardRequest the card request
     * @return the card response
     * @throws IOException the io exception
     */
    public CardResponse createCard(CardRequest cardRequest) throws IOException {
        cardRequestValidation(cardRequest);
        ModelMapper reqModelMapper = new ModelMapper();
        Card card = reqModelMapper.map(cardRequest, Card.class);
        card.setCreated_date(new Date());
        card.setUpdated_date(new Date());
        card.setActive(true);

        Card response = cardRepository.save(card);

        ModelMapper resModelMapper = new ModelMapper();
        CardResponse cardResponse = resModelMapper.map(response, CardResponse.class);

        ClassPathResource classPathResource = new ClassPathResource("img/favicon.png");
        byte[] bytes = new byte[(int) classPathResource.contentLength()];
        Icon icon = iconRepository.save(new Icon("favicon.png", "image/png", response.getId(), DigitalUtil.compressBytes(bytes)));

        if(!cardInGroupRepository.fetchGrupByCardId(response.getId()).isEmpty()) {
            cardRequest.setExpire_date(null);
        }

        if (cardRequest.getOriginal_url() != null) {
            Url newUrl = createShortUrl(cardRequest.getOriginal_url(), cardRequest.getExpire_date(), response.getId());
            cardRepository.updateAddress(newUrl.getId(), icon.getId(), response.getId());
            cardResponse.setOriginal_url(newUrl.getLong_url());
            cardResponse.setShort_url(newUrl.getShort_url());
            cardResponse.setExpire_date(newUrl.getCreated_date());
        }
        cardResponse.setHasAdmin(true);
        return cardResponse;

    }

    /**
     * Card request validation.
     *
     * @param cardRequest the card request
     */
    public void cardRequestValidation(CardRequest cardRequest) {
        if (!DigitalUtil.isEmailValid(cardRequest.getCreated_by()) || !DigitalUtil.isEmailValid(cardRequest.getUpdated_by())) {
            throw new BadRequestException("Email is not valid");
        }
        if (!DigitalUtil.isUrlValid(cardRequest.getOriginal_url())) {
            throw new BadRequestException("Original Url is not valid");
        }
    }

    /**
     * Gets card by id.
     *
     * @param cardId the card id
     * @return the card by id
     */
    public Card getCardById(int cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        if(card.isPresent()) {
            return card.get();
        }
        else {
            return new Card();
        }
    }

    /**
     * Uplaod image.
     *
     * @param icon the icon
     */
    public void uplaodImage(Icon icon) {
        iconRepository.save(icon);
    }

    /**
     * Download image icon.
     *
     * @param cardId the card id
     * @return the icon
     */
    public Icon downloadImage(int cardId) {
        Optional<Icon> icon = iconRepository.findById(getCardById(cardId).getIcon_id());
        if(icon.isPresent()) {
            return icon.get();
        }
        else {
            return new Icon();
        }
    }

    /**
     * Delete card.
     *
     * @param card  the card
     * @param email the email
     */
    public void deleteCard(Card card, String email) {
        if (card.getCreated_by().equals(email)) {
            suggestionQueueRepository.deleteSuggestionQueueByCardId(card.getId());
            cardInGroupRepository.deleteCardInGroup(card.getId());
            iconRepository.deleteById(card.getIcon_id());
            urlRepository.deleteById(card.getUrl_id());
            cardRepository.deleteById(card.getId());
        } else {
            throw new DigitalOrgException("You are not an admin of this group.");
        }

    }


    /**
     * Gets all card.
     *
     * @param emailId the email id
     * @return the all card
     */
    public List<CardResponse> getAllCard(String emailId) {
        List<CardResponse> cardList = cardRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, CardResponse.class), Collectors.toList()));

        List<CardResponse> cardResponseList = cardList.stream().map(cardResponse -> {

            Collection<Url> list = urlRepository.fetchURLObjectByCardId(cardResponse.getId());
            if(!list.isEmpty()) {
                Url url = list.iterator().next();
                cardResponse.setOriginal_url(url.getLong_url());
                cardResponse.setShort_url(url.getShort_url());
                cardResponse.setExpire_date(url.getExpires_date());
            }
            if (cardResponse.getCreated_by().equals(emailId)) {
                cardResponse.setHasAdmin(true);
            } else {
                cardResponse.setHasAdmin(false);
            }
            return cardResponse;
        }).collect(Collectors.toList());
        return cardResponseList;
    }

    /**
     * Gets allcard for owner.
     *
     * @param emailId the email id
     * @return the allcard for owner
     */
    public List<CardResponse> getAllcardForOwner(String emailId) {
        List<CardResponse> cardResponseList = getAllCard(emailId);
        return cardResponseList.stream().filter(cardResponse -> cardResponse.getHasAdmin() == true).collect(Collectors.toList());
    }

    /**
     * Update card object.
     *
     * @param cardRequest the card request
     * @return the object
     */
    public CardResponse updateCard(CardUpdateRequest cardRequest) {
        CardResponse cardResponse = new CardResponse();
        Optional<Card> cardOptional = cardRepository.findById(cardRequest.getId());
        String shortUrl = null;
        Card card = cardOptional.get();
        if (card != null && card.getCreated_by().equals(cardRequest.getUpdated_by())) {
            ModelMapper reqModelMapper = new ModelMapper();
            Card cardMapper = reqModelMapper.map(cardRequest, Card.class);
            cardMapper.setUpdated_date(new Date());
            cardMapper.setCreated_by(card.getCreated_by());
            cardMapper.setCreated_date(card.getCreated_date());
            cardMapper.setActive(card.getActive());
            cardMapper.setIcon_id(card.getIcon_id());
            cardMapper.setUrl_id(card.getUrl_id());
            if (cardRequest.getOriginal_url() != null) {
                shortUrl = updateShortUrl(card, cardRequest.getOriginal_url(), cardRequest.getExpire_date(), card.getUrl_id());

            }

            if(!cardInGroupRepository.fetchGrupByCardId(card.getId()).isEmpty()) {
                cardRequest.setExpire_date(null);
            }
            cardRepository.save(cardMapper);
            ModelMapper resModelMapper = new ModelMapper();
            cardResponse = resModelMapper.map(cardMapper, CardResponse.class);
            cardResponse.setHasAdmin(true);
            cardResponse.setOriginal_url(cardRequest.getOriginal_url());
            cardResponse.setShort_url(shortUrl);
            cardResponse.setExpire_date(cardRequest.getExpire_date());
            return cardResponse;
        }
        else {
            throw new BadRequestException("You are not the owner of this card."+ cardRequest.getTitle());
        }
    }

    /**
     * Update short url.
     *
     * @param card        the card
     * @param originalUrl the original url
     * @param date        the date
     * @param urlId       the url id
     * @return the string
     */
    public String updateShortUrl(Card card, String originalUrl, Date date, int urlId) {
        String shortUrl = baseConversion.encode(card.getId());
        urlRepository.updateUrl(originalUrl, shortUrl, date, urlId);
        return shortUrl;
    }

    /**
     * Suggestion for card suggestion queue.
     *
     * @param suggestionQueueRequest the suggestion queue request
     * @return the suggestion queue
     * @throws NotFoundException the not found exception
     */
    public SuggestionQueue suggestionForCard(SuggestionQueueRequest suggestionQueueRequest) throws NotFoundException {
        Optional<Card> card = cardRepository.findById(suggestionQueueRequest.getCard_id());
        if (card.isPresent() && card.get() != null) {
            SuggestionQueue suggestionQueue = new SuggestionQueue();
            suggestionQueue.setCard_id(suggestionQueueRequest.getCard_id());
            suggestionQueue.setEmail(suggestionQueueRequest.getEmail());
            suggestionQueue.setSuggested_date(new Date());
            suggestionQueue.setSuggestion_text(suggestionQueueRequest.getSuggestion_text());
            return suggestionQueueRepository.save(suggestionQueue);
        }
        else {
            throw new NotFoundException("Requested card is not available for suggestion.");
        }
    }

    /**
     * Gets all suggestion for card.
     *
     * @param email the email
     * @return the all suggestion for card
     */
    public List<SuggestionQueue> getAllSuggestionForCard(String email) {
        List<CardResponse> cardResponseList = getAllCard(email);
        List <SuggestionQueue> result = new ArrayList<>();
        cardResponseList.stream().forEach((cardResponse -> result.addAll(suggestionQueueRepository.getAllSuggestionQueueByCardId(cardResponse.getId()))));
       return result;
    }
}
