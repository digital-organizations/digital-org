package com.engg.digitalorg.services;

import co.elastic.apm.api.CaptureSpan;
import com.engg.digitalorg.api.CardApi;
import com.engg.digitalorg.exception.BadRequestException;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.CardManager;
import com.engg.digitalorg.model.entity.Icon;
import com.engg.digitalorg.model.entity.SuggestionQueue;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.request.CardUpdateRequest;
import com.engg.digitalorg.model.request.SuggestionQueueRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.model.response.IconResponse;
import com.engg.digitalorg.util.DigitalUtil;
import javassist.NotFoundException;
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

/**
 * The type Card service.
 */
@RestController("CardService")
public class CardService implements CardApi {

    @Autowired
    private CardManager cardManager;

    @Override
    public ResponseEntity<CardResponse> createCard(CardRequest cardRequest) throws DigitalOrgException, IOException {
        try {
            return new ResponseEntity<>(cardManager.createCard(cardRequest), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<CardResponse> updateCard(CardUpdateRequest cardRequest) throws DigitalOrgException, IOException {
        return new ResponseEntity<>(cardManager.updateCard(cardRequest), HttpStatus.OK);
    }

    public ResponseEntity<CardResponse> uplaodImage(int cardId, @RequestParam("file") MultipartFile file) throws IOException {
        Icon icon = new Icon(file.getOriginalFilename(), file.getContentType(), cardId, DigitalUtil.compressBytes(file.getBytes()));
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
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("image/png")).body(resource);
    }

    @Override
    public ResponseEntity deleteCard(int cardId, String email) throws DigitalOrgException {
        cardManager.deleteCard(cardManager.getCardById(cardId), email);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @CaptureSpan(value = "getAllCard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllcard(String email) throws DigitalOrgException {
        return new ResponseEntity<>(cardManager.getAllCard(email), HttpStatus.OK);
    }

    @Override
    @CaptureSpan(value = "getAllCard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllcardForOwner(String email) throws DigitalOrgException {
        return new ResponseEntity<>(cardManager.getAllcardForOwner(email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SuggestionQueue> suggestionForCard(SuggestionQueueRequest suggestionQueueRequest) throws DigitalOrgException, NotFoundException {
        return new ResponseEntity<>(cardManager.suggestionForCard(suggestionQueueRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity suggestionForCard(int suggestionId) throws DigitalOrgException, NotFoundException {
        cardManager.deleteSuggestionForCard(suggestionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<SuggestionQueue>> getAllSuggestionForCard(String email) throws DigitalOrgException, NotFoundException {
        return new ResponseEntity<>(cardManager.getAllSuggestionForCard(email), HttpStatus.OK);
    }

}
