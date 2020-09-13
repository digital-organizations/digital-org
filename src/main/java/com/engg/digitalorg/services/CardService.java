package com.engg.digitalorg.services;

import com.engg.digitalorg.api.CardApi;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.CardManager;
import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.request.CardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.Deflater;

@RestController("CardService")
public class CardService implements CardApi {

    @Autowired
    private CardManager cardManager;

    @Override

    public ResponseEntity<CardResponse> createCard(CardRequest cardRequest, ModelMap modelMap) throws DigitalOrgException, IOException {
        modelMap.addAttribute("cardRequest", cardRequest);
        return new ResponseEntity<>(cardManager.createCard(cardRequest), HttpStatus.OK);
    }

    public ResponseEntity<Resource> uplaodImage(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(file.getBytes());
        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }

    @Override
    public Card getCard(Integer cardId) throws DigitalOrgException {
        return cardManager.getCard(cardId);
        //TODO : only authorize card display
    }

    @Override
    public List<Card> getAllCard() throws DigitalOrgException {
        return cardManager.getAllCard();
    }
}
