package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.Icon;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.model.response.IconResponse;
import io.swagger.annotations.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/card")
@Api(tags = "Card Services", description = "Card Api")
@CrossOrigin(origins = {"https://digital-org.herokuapp.com/", "http://localhost:4200"})
public interface CardApi {

    @ApiOperation(value = "Create new card", nickname = "createCard", notes = "User to create New card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class),
                           @ApiResponse(code = 400, message = "Invalid Request")})
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity createCard(@RequestBody CardRequest cardRequest) throws DigitalOrgException, IOException;

    @ApiOperation(value = "Create new card", nickname = "createCard", notes = "User to create New card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Card type unknown")})
    @PostMapping(path = "/upload/{card-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<CardResponse> uplaodImage(@ApiParam(name = "file", value = "Select the file to Upload", required = true)  @PathVariable("card-id") int cardId , @RequestPart("file") MultipartFile file) throws IOException ;

    @ApiOperation(value = "Create new card", nickname = "createCard", notes = "User to create New card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Card type unknown")})
    @GetMapping(path = "/download/{card-id}")
    public ResponseEntity<IconResponse> downloadImage(@PathVariable("card-id") int cardId) throws IOException ;

    @ApiOperation(value = "export card", nickname = "exportCard", notes = "export all card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Card type unknown")})
    @GetMapping(path = "/all", produces = "application/json")
    List<CardResponse> getAllCard() throws DigitalOrgException;

}
