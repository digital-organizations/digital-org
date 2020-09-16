package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.request.CardRequest;
import com.engg.digitalorg.model.request.CardUpdateRequest;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.model.response.IconResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * The interface Card api.
 */
@RequestMapping("/card")
@Api(tags = "Card Services", description = "Card Api")
@CrossOrigin(origins = {"https://digital-org.herokuapp.com/", "http://localhost:4200"})
public interface CardApi {

    /**
     * Create card response entity.
     *
     * @param cardRequest the card request
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     * @throws IOException         the io exception
     */
    @ApiOperation(value = "Create new card", notes = "User to create new card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Invalid Request")})
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity createCard(@RequestBody CardRequest cardRequest) throws DigitalOrgException, IOException;

    /**
     * Update card response entity.
     *
     * @param cardRequest the card request
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     * @throws IOException         the io exception
     */
    @ApiOperation(value = "Update existing card", notes = "User to update existing card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Invalid Request")})
    @PatchMapping(path = "/update", consumes = "application/json", produces = "application/json")
    ResponseEntity updateCard(@RequestBody CardUpdateRequest cardRequest) throws DigitalOrgException, IOException;

    /**
     * Uplaod image response entity.
     *
     * @param cardId the card id
     * @param file   the file
     * @return the response entity
     * @throws IOException the io exception
     */
    @ApiOperation(value = "Upload Image", notes = "Upload custom image", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Unknown image type")})
    @PostMapping(path = "/upload/{card-id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
    public ResponseEntity<CardResponse> uplaodImage(@PathVariable("card-id") int cardId, @RequestPart("file") MultipartFile file) throws IOException;

    /**
     * Download image response entity.
     *
     * @param cardId the card id
     * @return the response entity
     * @throws IOException the io exception
     */
    @ApiOperation(value = "Download image", notes = "Download image for card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(path = "/download/{card-id}")
    public ResponseEntity<IconResponse> downloadImage(@PathVariable("card-id") int cardId) throws IOException;

    /**
     * Download imageocta response entity.
     *
     * @param cardId the card id
     * @return the response entity
     * @throws IOException the io exception
     */
    @ApiOperation(value = "Download image", notes = "Download image for card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping(path = "/download-octa/{card-id}")
    public ResponseEntity<Resource> downloadImageocta(@PathVariable("card-id") int cardId) throws IOException;


    /**
     * Delete card response entity.
     *
     * @param cardId the card id
     * @param email  the email
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Delete card", notes = "Delete requested card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Deleted", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping(path = "/{card-id}/{email}", produces = "application/json")
    ResponseEntity deleteCard(@PathVariable("card-id") int cardId, @PathVariable("email") String email) throws DigitalOrgException;

    /**
     * Gets allcard.
     *
     * @param email the email
     * @return the allcard
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All cards ", notes = "Get all card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @PostMapping(path = "/all", produces = "application/json")
    public ResponseEntity<List> getAllcard(@RequestBody String email) throws DigitalOrgException;
}
