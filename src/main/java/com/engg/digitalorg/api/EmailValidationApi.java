package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/email-validate")
@Api(tags = "Email Validation Services", description = "Card Api")
@CrossOrigin(origins = {"https://digital-org.herokuapp.com/", "http://localhost:4200"})
public interface EmailValidationApi {

    @ApiOperation(value = "email validation ", nickname = "validateEmail", notes = "User to get a card", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "email type unknown")})
    @PostMapping(path = "/", produces = "application/json")
    public ResponseEntity<Map> emailValidation(@ApiParam(value = "email", required = true) @RequestBody String email) throws DigitalOrgException;
}
