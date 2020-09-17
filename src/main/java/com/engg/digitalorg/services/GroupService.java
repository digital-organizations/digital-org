package com.engg.digitalorg.services;

import co.elastic.apm.api.CaptureSpan;
import com.engg.digitalorg.api.GroupApi;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.GroupManager;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.request.CardInGroupRequest;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.request.GroupUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Group service.
 */
@RestController("GroupService")
public class GroupService implements GroupApi {

    @Autowired
    private GroupManager groupManager;

    @Override
    public ResponseEntity createGroup(GroupRequest groupRequest) throws DigitalOrgException {
        return new ResponseEntity<>(groupManager.createGroup(groupRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateGroup(GroupUpdateRequest groupRequest) throws DigitalOrgException {
        return new ResponseEntity<>(groupManager.updateGroup(groupRequest), HttpStatus.OK);
    }


    @Override
    public Group getGroupbyId(Integer cardId) throws DigitalOrgException {
        return groupManager.getGroupById(cardId);
    }


    @Override
    public ResponseEntity addUserToGroup(String userEmail, String adminEmail, int groupId) throws DigitalOrgException {
        groupManager.addUserToGroup(userEmail, adminEmail, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeUserFromGroup(String email, int groupId) throws DigitalOrgException {
        groupManager.removeUserToGroup(email, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @CaptureSpan(value = "getAllcard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllGroupService(String email) throws DigitalOrgException {
        return new ResponseEntity<>(groupManager.getAllGroupManager(email), HttpStatus.OK);
    }

    @Override
    @CaptureSpan(value = "getAllcard", type = "service", subtype = "http")
    public ResponseEntity<List> getAllGroupServiceforOwner(String email) throws DigitalOrgException {
        return new ResponseEntity<>(groupManager.getAllGroupManagerForOwner(email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity addCardToGroup(CardInGroupRequest cardInGroupRequest) throws DigitalOrgException {
        return new ResponseEntity<>(groupManager.addCardToGroupManager(cardInGroupRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeCardToGroup(CardInGroupRequest cardInGroupRequest) throws DigitalOrgException {
        return new ResponseEntity<>(groupManager.removeCardToGroupManager(cardInGroupRequest), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getAllCardForGroup(int groupId) throws DigitalOrgException {
        return new ResponseEntity<>(groupManager.getAllCardForGroupManager(groupId), HttpStatus.OK);
    }
}
