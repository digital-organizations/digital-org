package com.engg.digitalorg.services;

import com.engg.digitalorg.api.GroupApi;
import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.managers.GroupManager;
import com.engg.digitalorg.model.Group;
import com.engg.digitalorg.model.RequestGroup;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("GroupService")
public class GroupService implements GroupApi {

    @Autowired
    private GroupManager groupManager;


    @Override
    public List<Group> getAllGroup() throws DigitalOrgException {
        return groupManager.getAllGroup();
    }

    @Override
    public Group createGroup(RequestGroup requestGroup) throws DigitalOrgException {
        ModelMapper modelMapper = new ModelMapper();
        Group group = modelMapper.map(requestGroup, Group.class);
        return groupManager.createGroup(group);
    }

    @Override
    public Group getGroupbyId(Integer cardId) throws DigitalOrgException {
        return groupManager.getGroupById(cardId);
    }

    @Override
    public Group updateGroup(RequestGroup requestGroup, Integer groupId) throws DigitalOrgException {
        //TODO : Valid group validation
        //TODO : only admin can update the group
        ModelMapper modelMapper = new ModelMapper();
        Group group = modelMapper.map(requestGroup, Group.class);
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
}
