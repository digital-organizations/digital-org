package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.request.CardInGroupRequest;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.request.GroupUpdateRequest;
import com.engg.digitalorg.model.request.UserToGroupRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The interface Group api.
 */
@RequestMapping("/group")
@Api(tags = "Group Services", description = "Group Api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface GroupApi {

    /**
     * Create group response entity.
     *
     * @param group the group
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Create new group", notes = "User to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity createGroup(@ApiParam(value = "group object", required = true) @RequestBody GroupRequest group) throws DigitalOrgException;

    /**
     * Update group response entity.
     *
     * @param group the group
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Update existing group", notes = "User to create New group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Bad Request")})
    @PatchMapping(path = "/update", consumes = "application/json", produces = "application/json")
    ResponseEntity updateGroup(@RequestBody GroupUpdateRequest group) throws DigitalOrgException;

    /**
     * Gets groupby id.
     *
     * @param cardId the card id
     * @return the groupby id
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "delete group", notes = "delete Group", response = Group.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = Group.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @GetMapping(path = "/{group-id}", produces = "application/json")
    Group getGroupbyId(@ApiParam(value = "group object", required = true) @PathVariable("card-id") Integer cardId) throws DigitalOrgException;

    /**
     * Add user to group response entity.
     *
     * @param userEmail  the user email
     * @param adminEmail the admin email
     * @param groupId    the group id
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "add user to group", notes = "add user to group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PostMapping(path = "/add-user-to-group", consumes = "application/json", produces = "application/json")
    ResponseEntity addUserToGroup(@RequestBody UserToGroupRequest userToGroupRequest) throws DigitalOrgException;

    /**
     * Remove user from group response entity.
     *
     * @param userEmail  the user email
     * @param adminEmail the admin email
     * @param groupId    the group id
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "remove user from group", notes = "remove User from group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "No content", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PostMapping(path = "/remove-user-from-group", consumes = "application/json", produces = "application/json")
    ResponseEntity removeUserFromGroup(@RequestBody UserToGroupRequest userToGroupRequest) throws DigitalOrgException;

    /**
     * Gets all group service.
     *
     * @param email the email
     * @return the all group service
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All group ", notes = "Get all group", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @GetMapping(path = "/all/{email}", produces = "application/json")
    ResponseEntity<List> getAllGroupService(@ApiParam(value = "email", required = true) @PathVariable("email") String email) throws DigitalOrgException;

    /**
     * Gets all group servicefor owner.
     *
     * @param email the email
     * @return the all group servicefor owner
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All group for owner ", notes = "Get all group for owner", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ok", response = ResponseEntity.class), @ApiResponse(code = 400, message = "bad request")})
    @GetMapping(path = "/all/admin/{email}", produces = "application/json")
    ResponseEntity<List> getAllGroupServiceforOwner(@ApiParam(value = "email", required = true) @PathVariable("email") String email) throws DigitalOrgException;

    /**
     * Add card to group response entity.
     *
     * @param cardInGroupRequest the card in group request
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Add card to group", notes = "Admin of group can be add card.", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PostMapping(path = "/add-card-to-group", consumes = "application/json", produces = "application/json")
    ResponseEntity addCardToGroup(@ApiParam(value = "email", required = true) @RequestBody CardInGroupRequest cardInGroupRequest) throws DigitalOrgException;

    /**
     * Remove card to group response entity.
     *
     * @param cardInGroupRequest the card in group request
     * @return the response entity
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Remove user to group", notes = "Admin of group can be remove card.", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PostMapping(path = "/remove-card-from-group", consumes = "application/json", produces = "application/json")
    ResponseEntity removeCardToGroup(@ApiParam(value = "email", required = true) @RequestBody CardInGroupRequest cardInGroupRequest) throws DigitalOrgException;

    /**
     * Gets all card for group.
     *
     * @param groupId the group id
     * @return the all card for group
     * @throws DigitalOrgException the digital org exception
     */
    @ApiOperation(value = "Get All card from group", notes = "Get All card from group.", response = ResponseEntity.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = ResponseEntity.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @GetMapping(path = "/card-from-group/{group-id}", consumes = "*/*", produces = "application/json")
    ResponseEntity<List> getAllCardForGroup(@PathVariable("group-id") int groupId) throws DigitalOrgException;

}
