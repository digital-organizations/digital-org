package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.RequestCard;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/card")
@Api(tags = "Card Services", description = "Card Api")
@CrossOrigin(origins = {"https://digital-org.herokuapp.com/", "http://localhost:4200"})
public interface CardApi {

    @ApiOperation(value = "Create new card", nickname = "createCard", notes = "User to create New card", response = Card.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = Card.class),
                           @ApiResponse(code = 400, message = "Card type unknown")})
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    Card createCard(@ApiParam(value = "card object", required = true) @RequestBody RequestCard card) throws DigitalOrgException;

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
