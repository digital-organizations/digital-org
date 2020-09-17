package com.engg.digitalorg.managers;

import com.engg.digitalorg.exception.NotFoundException;
import com.engg.digitalorg.model.entity.Card;
import com.engg.digitalorg.model.entity.Url;
import com.engg.digitalorg.repository.CardRepository;
import com.engg.digitalorg.repository.UrlRepository;
import com.engg.digitalorg.util.BaseConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The type Url manager.
 */
@Component
public class UrlManager {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private CardRepository cardRepository;

    /**
     * The Base conversion.
     */
    @Autowired
    BaseConversion baseConversion;


    /**
     * Gets original url.
     *
     * @param shortUrl the short url
     * @return the original url
     */
    public String getOriginalUrl(String shortUrl) {
        Optional<Url> entity;
        try {
            int id = baseConversion.decode(shortUrl);

            Optional<Card> card = cardRepository.findById(id);
            entity = urlRepository.findById(card.get().getUrl_id());
            if (entity.get() == null) {
                throw new NotFoundException("There is no record with " + shortUrl);
            }
            if (entity.get().getExpires_date() != null && entity.get().getExpires_date().before(new Date())) {
//                urlRepository.delete(entity.get());
                throw new NotFoundException("Link expired!");
            }
        } catch (NoSuchElementException exception) {
            throw new NotFoundException("There is no record with " + shortUrl);
        }
        return entity.get().getLong_url();
    }
}
