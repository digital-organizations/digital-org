package com.engg.digitalorg.managers;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.Url;
import com.engg.digitalorg.repository.UrlRepository;
import com.engg.digitalorg.util.BaseConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
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
            entity = urlRepository.findById(id);
            if (entity.get() == null) {
                throw new EntityNotFoundException("There is no record with " + shortUrl);
            }
            if (entity.get().getExpires_date() != null && entity.get().getExpires_date().before(new Date())) {
                urlRepository.delete(entity.get());
                throw new EntityNotFoundException("Link expired!");
            }
        } catch (NoSuchElementException exception) {
            throw new DigitalOrgException("There is no record with " + shortUrl);
        }


        return entity.get().getLong_url();
    }
}
