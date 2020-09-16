package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.request.GroupUpdateRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/group")
@Api(tags = "Group Services", description = "Group Api")
@CrossOrigin(origins = {"https://digital-org.herokuapp.com/", "http://localhost:4200"})
public interface GroupApi {

    @ApiOperation(value = "Create new group", notes = "User to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity createGroup(@ApiParam(value = "group object", required = true) @RequestBody GroupRequest group) throws DigitalOrgException;

    @ApiOperation(value = "Update existing group", notes = "User to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PatchMapping(path = "/update", consumes = "application/json", produces = "application/json")
    ResponseEntity updateGroup(@RequestBody GroupUpdateRequest group) throws DigitalOrgException;

    @ApiOperation(value = "delete group", notes = "delete Group", response = Group.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = Group.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @GetMapping(path = "/{group-id}", produces = "application/json")
    public Group getGroupbyId(@ApiParam(value = "group object", required = true) @PathVariable("card-id") Integer cardId) throws DigitalOrgException;

    @ApiOperation(value = "add user to group", notes = "add user to group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PostMapping(path = "/user-to-group", consumes = "application/json", produces = "application/json")
    ResponseEntity addUserToGroup(@ApiParam(value = "email", required = true) @RequestBody String userEmail, String adminEmail, int groupId) throws DigitalOrgException;

    @ApiOperation(value = "remove user from group", notes = "remove User from group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @DeleteMapping(path = "/user-from-group", consumes = "application/json", produces = "application/json")
    ResponseEntity removeUserFromGroup(@ApiParam(value = "email", required = true) @RequestBody String email, int groupId) throws DigitalOrgException;

    @ApiOperation(value = "Get All group ", notes = "Get all group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @GetMapping(path = "/all/{email}", produces = "application/json")
    public ResponseEntity<List> getAllGroupService(@ApiParam(value = "email", required = true) @PathVariable("email") String email) throws DigitalOrgException;
}
