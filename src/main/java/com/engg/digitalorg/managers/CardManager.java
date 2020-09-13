package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.entity.Card;
import com.engg.digitalorg.model.entity.Icon;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.repository.CardRepository;
import com.engg.digitalorg.repository.IconRepository;
import com.engg.digitalorg.util.DigitalMultipartFile;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Component
public class CardManager {

    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = allowedString.toCharArray();
    private int base = allowedCharacters.length;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    IconRepository iconRepository;

    public CardResponse createCard(CardRequest cardRequest)  throws IOException {
        ModelMapper reqModelMapper = new ModelMapper();
        Card card = reqModelMapper.map(cardRequest, Card.class);
        card.setCreated_date(new Date());
        card.setUpdated_date(new Date());

        if(cardRequest.getGroup_name() != null) {
//        TODO: build short url without expire if group,

        }
        else {
//        TODO: build short url with expire,
            StringBuilder encodedString = new StringBuilder();
            int input= 1;
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }
/*
            if(card.getFile() == null) {
                ClassPathResource classPathResource = new ClassPathResource("files/favicon.png");
                byte[] bytes = new byte[(int) classPathResource.contentLength()];
                classPathResource.getInputStream().read(bytes);
                card.setFile(compressBytes(bytes));

                card.setFile(compressBytes(cardRequest.getFile().getBytes()));

            }
*/

        Card response = cardRepository.save(card);

        ModelMapper resModelMapper = new ModelMapper();
        CardResponse cardResponse = resModelMapper.map(response, CardResponse.class);
//        MultipartFile file = new DigitalMultipartFile(decompressBytes(response.getFile()));
//        cardResponse.setFile(file);

        return cardResponse;
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

    public Card getCard(Integer cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        return card.get();
    }

    public List<CardResponse> getAllCard() {
        List<CardResponse> cardList = cardRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, CardResponse.class), Collectors.toList()));
        return cardList;
    }

    public void uplaodImage(Icon icon) {
        iconRepository.save(icon);
    }

    public Icon downloadImage(int cardId) {
        Optional<Icon> icon = iconRepository.findById(cardId);
        return icon.get();
    }
}
