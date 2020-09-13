package com.engg.digitalorg.services;

import com.engg.digitalorg.api.GroupApi;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.GroupManager;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.request.GroupRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController("GroupService")
public class GroupService implements GroupApi {

    @Autowired
    private GroupManager groupManager;

    @Override
    public Group createGroup(GroupRequest groupRequest) throws DigitalOrgException {
        ModelMapper modelMapper = new ModelMapper();
        Group group = modelMapper.map(groupRequest, Group.class);
        group.setCreated_date(new Date());
        group.setUpdated_date(new Date());
        return groupManager.createGroup(group);
    }

    @Override
    public List<Group> getAllGroup() throws DigitalOrgException {
        return groupManager.findAllActiveGroup();
    }

    @Override
    public Group getGroupbyId(Integer cardId) throws DigitalOrgException {
        return groupManager.getGroupById(cardId);
    }

    @Override
    public Group updateGroup(GroupRequest groupRequest, Integer groupId) throws DigitalOrgException {
        //TODO : Valid group validation
        //TODO : only admin can update the group
        ModelMapper modelMapper = new ModelMapper();
        Group group = modelMapper.map(groupRequest, Group.class);
        group.setId(groupId);
        return groupManager.updateGroup(group);
    }

    @Override
    public void deleteGroup(Integer groupId) throws DigitalOrgException {
//        Group group = groupManager.getGroupById(groupId);
        //TODO : Valid group validation
        //TODO : only admin can update the group
        //TODO : By deleting group will de-associate all the cards from that group
        groupManager.deleteGroup(groupId);
    }

    @Override
    public ResponseEntity addUserToGroup(String user, String admin, int groupId) throws DigitalOrgException {
//        TODO : validation : admin is valid admin for given group
        groupManager.addUserToGroup(user, groupManager.getGroupById(groupId).getName(), admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity removeUserFromGroup(String user, String admin, int groupId) throws DigitalOrgException {
        //        TODO : validation : admin is valid admin for given group
        groupManager.removeUserToGroup(user, groupManager.getGroupById(groupId).getName(), admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
