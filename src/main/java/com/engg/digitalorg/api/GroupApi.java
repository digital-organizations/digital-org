package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.response.GroupResponse;
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
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    ResponseEntity createGroup(@ApiParam(value = "group object", required = true) @RequestBody GroupRequest group) throws DigitalOrgException;

    @ApiOperation(value = "Update existing group", nickname = "updateGroup", notes = "User to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PutMapping(path = "/{group-id}", consumes = "application/json", produces = "application/json")
    ResponseEntity updateGroup(@ApiParam(value = "group object", required = true) @RequestBody GroupRequest group, @PathVariable("group-id") int groupId) throws DigitalOrgException;

    @ApiOperation(value = "delete group", nickname = "deleteGroup", notes = "delete Group", response = Group.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = Group.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @GetMapping(path = "/{group-id}", produces = "application/json")
    public Group getGroupbyId(@ApiParam(value = "group object", required = true) @PathVariable("card-id") Integer cardId) throws DigitalOrgException;

    @ApiOperation(value = "delete group", nickname = "deleteGroup", notes = "delete Group", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "accepted operation", response = Void.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @DeleteMapping(path = "/{group-id}", produces = "application/json")
    public void deleteGroup(@ApiParam(value = "group object", required = true) @PathVariable("group-id") Integer groupId) throws DigitalOrgException;


    @ApiOperation(value = "add user to group", nickname = "createGroup", notes = "User to create New group", response = Group.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = Group.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PostMapping(path = "/add-user-to-group", consumes = "application/json", produces = "application/json")
    ResponseEntity addUserToGroup(@ApiParam(value = "group object", required = true) @RequestBody String user, String admin, int groupId) throws DigitalOrgException;

    @ApiOperation(value = "remove user from group", nickname = "createGroup", notes = "User to create New group", response = Group.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = Group.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PostMapping(path = "/remove-user-from-group", consumes = "application/json", produces = "application/json")
    ResponseEntity removeUserFromGroup(@ApiParam(value = "group object", required = true) @RequestBody String user, String admin,int groupId) throws DigitalOrgException;
}
