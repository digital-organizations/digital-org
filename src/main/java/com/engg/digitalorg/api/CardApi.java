package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.request.CardRequest;
import io.swagger.annotations.*;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/card")
@Api(tags = "Card Services", description = "Card Api")
@CrossOrigin(origins = {"https://digital-org.herokuapp.com/", "http://localhost:4200"})
public interface CardApi {

    @ApiOperation(value = "Create new card", nickname = "createCard", notes = "User to create New card", response = Card.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = Card.class),
                           @ApiResponse(code = 400, message = "Card type unknown")})
    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    ResponseEntity createCard(@ApiParam(name = "file", value = "Select the file to Upload", required = true)
                              @ModelAttribute CardRequest card, ModelMap modelMap) throws DigitalOrgException, IOException;

    @ApiOperation(value = "Create new card", nickname = "createCard", notes = "User to create New card", response = Card.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = Card.class),
            @ApiResponse(code = 400, message = "Card type unknown")})
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<Resource> uplaodImage(@ApiParam(name = "file", value = "Select the file to Upload", required = true) @RequestPart("file") MultipartFile file) throws IOException ;

    @ApiOperation(value = "get card detail", nickname = "getCard", notes = "User to get a card", response = Card.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = Card.class),
            @ApiResponse(code = 400, message = "Card type unknown")})
    @GetMapping(path = "/{card-id}", produces = "application/json")
    public Card getCard(@ApiParam(value = "card object", required = true) @PathVariable("card-id") Integer cardId) throws DigitalOrgException;

    @ApiOperation(value = "export card", nickname = "exportCard", notes = "export all card", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = List.class),
            @ApiResponse(code = 400, message = "Card type unknown")})
    @GetMapping(path = "/all", produces = "application/json")
    public List<Card> getAllCard() throws DigitalOrgException;

}
