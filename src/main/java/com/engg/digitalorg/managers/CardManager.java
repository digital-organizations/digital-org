package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.request.CardResponse;
import com.engg.digitalorg.repository.CardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Component
public class CardManager {

    @Autowired
    private CardRepository cardRepository;

    public Card getCard(Integer cardId) {
        Optional<Card> card = cardRepository.findById(cardId);
        return card.get();
    }

    public CardResponse createCard(CardRequest cardRequest)  throws IOException {
        ModelMapper reqModelMapper = new ModelMapper();
        Card card = reqModelMapper.map(cardRequest, Card.class);
        card.setFile(compressBytes(cardRequest.getFile().getBytes()));
//        TODO: build short url with expire,
//        TODO: build short url without expire if group,
//        TODO: generate created by, created date, modified by, modified date

        if(card.getFile() == null) {
            ClassPathResource jsaCoverImgFile = new ClassPathResource("files/favicon.png");
            byte[] arrayData = new byte[(int) jsaCoverImgFile.contentLength()];
            jsaCoverImgFile.getInputStream().read(arrayData);
            card.setFile(arrayData);
        }

        Card response = cardRepository.save(card);
        response.setFile(decompressBytes(response.getFile()));
        ModelMapper resModelMapper = new ModelMapper();
        CardResponse cardResponse = resModelMapper.map(response, CardResponse.class);
        return cardResponse;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    public List<Card> getAllCard() {
        return cardRepository.findAll();
    }
}
