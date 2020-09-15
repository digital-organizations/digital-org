package com.engg.digitalorg.services;

import co.elastic.apm.api.CaptureSpan;
import com.engg.digitalorg.api.CardApi;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.CardManager;
import com.engg.digitalorg.model.entity.Card;
import com.engg.digitalorg.model.entity.Icon;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.model.response.IconResponse;
import com.engg.digitalorg.util.DigitalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController("CardService")
public class CardService implements CardApi {

    @Autowired
    private CardManager cardManager;

    @Override
    public ResponseEntity<CardResponse> createCard(CardRequest cardRequest) throws DigitalOrgException, IOException {
        if(!DigitalUtil.isValid(cardRequest.getCreated_by()) || !DigitalUtil.isValid(cardRequest.getUpdated_by())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cardManager.createCard(cardRequest), HttpStatus.CREATED);
    }

    public ResponseEntity uplaodImage(int cardId, @RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Icon icon = new Icon(file.getOriginalFilename(), file.getContentType(), DigitalUtil.compressBytes(file.getBytes()),cardId);
        cardManager.uplaodImage(icon);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<IconResponse> downloadImage(int cardId) throws IOException {
        Icon icon = cardManager.downloadImage(cardId);
        IconResponse iconResponse = new IconResponse(icon.getName(), icon.getType(), DigitalUtil.decompressBytes(icon.getFile()), icon.getCard_id(), icon.getId());
        return new ResponseEntity<>(iconResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Resource> downloadImageocta(int cardId) throws IOException {
        Icon icon = cardManager.downloadImage(cardId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(icon.getFile());
        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }

    @Override
    public ResponseEntity deleteCard(int cardId, String email) throws DigitalOrgException {
        Card card = cardManager.getCardById(cardId);
        if(card.getCreated_by().equals(email)) {
            cardManager.deleteCard(cardId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @CaptureSpan(value = "getAllcard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllcard(String email) throws DigitalOrgException {
        return new ResponseEntity<>(cardManager.getAllCard(email), HttpStatus.OK);
    }

}
