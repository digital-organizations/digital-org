package com.engg.digitalorg.api;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.Group;
import com.engg.digitalorg.model.request.GroupRequest;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/group")
@Api(tags = "Group Services", description = "Group Api")
@CrossOrigin(origins = {"https://digital-org.herokuapp.com/", "http://localhost:4200"})
public interface GroupApi {

    @ApiOperation(value = "get all group", nickname = "getAllGroup", notes = "List all group", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = List.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @GetMapping(path = "/all", produces = "application/json")
    public List<Group> getAllGroup() throws DigitalOrgException;

    @ApiOperation(value = "Create new group", nickname = "createGroup", notes = "User to create New group", response = Group.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "accepted operation", response = Group.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    Group createGroup(@ApiParam(value = "group object", required = true) @RequestBody GroupRequest group) throws DigitalOrgException;

    @ApiOperation(value = "delete group", nickname = "deleteGroup", notes = "delete Group", response = Group.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = Group.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @GetMapping(path = "/{group-id}", produces = "application/json")
    public Group getGroupbyId(@ApiParam(value = "group object", required = true) @PathVariable("card-id") Integer cardId) throws DigitalOrgException;

    @ApiOperation(value = "Create new group", nickname = "createGroup", notes = "User to create New group", response = Group.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "accepted operation", response = Group.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @PutMapping(path = "/{group-id}", consumes = "application/json", produces = "application/json")
    Group updateGroup(@ApiParam(value = "group object", required = true) @RequestBody GroupRequest group, @PathVariable("card-id") Integer cardId) throws DigitalOrgException;

    @ApiOperation(value = "delete group", nickname = "deleteGroup", notes = "delete Group", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code = 204, message = "accepted operation", response = Void.class), @ApiResponse(code = 400, message = "Group type unknown")})
    @DeleteMapping(path = "/{group-id}", produces = "application/json")
    public void deleteGroup(@ApiParam(value = "group object", required = true) @PathVariable("group-id") Integer groupId) throws DigitalOrgException;
}
