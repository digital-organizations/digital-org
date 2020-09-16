package com.engg.digitalorg.managers;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.UserInGroup;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.request.GroupUpdateRequest;
import com.engg.digitalorg.model.response.GroupResponse;
import com.engg.digitalorg.repository.GroupRepository;
import com.engg.digitalorg.repository.UserInGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class GroupManager {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserInGroupRepository userInGroupRepository;

    public Group getGroup(Integer cardId) {
        Optional<Group> card = groupRepository.findById(cardId);
        return card.get();
    }

    public GroupResponse createGroup(GroupRequest groupRequest) {

        ModelMapper modelMapper = new ModelMapper();
        Group group = modelMapper.map(groupRequest, Group.class);
        group.setCreated_date(new Date());
        group.setUpdated_date(new Date());
        group.setActive(true);

        ModelMapper resModelMapper = new ModelMapper();
        GroupResponse groupResponse =resModelMapper.map(groupRepository.save(group), GroupResponse.class);
        groupResponse.setHasAdmin(true);
        return groupResponse;
    }

    public AtomicReference<GroupResponse> updateGroup(GroupUpdateRequest groupRequest) {
        AtomicReference<GroupResponse> groupResponse = null;
        Group group = getGroupById(groupRequest.getId());
        if(group == null) {
            throw new DigitalOrgException("Requested Group is not found.");
        }

        userInGroupRepository.findAllUserInGroupByGroupID(group.getId()).stream().map(userInGroup -> {
            if(userInGroup.getEmail().equals(groupRequest.getUpdated_by())) {
                userInGroupRepository.deleteById(userInGroup.getId());
            }
            ModelMapper modelMapper = new ModelMapper();
            Group groupMapper = modelMapper.map(groupRequest, Group.class);
            groupMapper.setUpdated_date(new Date());
            groupMapper.setActive(true);

            ModelMapper resModelMapper = new ModelMapper();
            groupResponse.set(resModelMapper.map(groupRepository.save(groupMapper), GroupResponse.class));
            groupResponse.get().setHasAdmin(true);

            return groupResponse;
        });
        return groupResponse;
    }

    public Group getGroupById(Integer cardId) {
        Optional<Group> group = groupRepository.findById(cardId);
        return group.get();
    }


    public void addUserToGroup(String userEmail, String adminEmail, int groupId) {
        List<UserInGroup> userInGroups =  userInGroupRepository.findAllUserInGroupByGroupID(groupId);
            userInGroups.stream().map(userInGroup -> {
                if(userInGroup.getEmail().equals(adminEmail)) {
                    UserInGroup user = new UserInGroup();
                    user.setEmail(userEmail);
                    user.setGroup_id(userInGroup.getGroup_id());
                    user.setAdded_date(new Date());
                    user.setAdded_by(adminEmail);
                    userInGroupRepository.save(user);
                }
                return "";
            });
    }

    public void removeUserToGroup(String email,int groupId) {
        List<UserInGroup> userInGroups =  userInGroupRepository.findAllUserInGroupByGroupID(groupId);
        if(userInGroups.size() > 1) {
            userInGroups.stream().map(userInGroup -> {
                if(userInGroup.getEmail().equals(email)) {
                    userInGroupRepository.deleteById(userInGroup.getId());
                }

                return "";
            });
        }
        else {
            throw new DigitalOrgException("You are only admin of this group, please other as a admin to this group, then remove");
        }
    }

    public List<GroupResponse> getAllGroupManager(String emailId) {
        List<GroupResponse> groupList = groupRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, GroupResponse.class), Collectors.toList()));

        List<GroupResponse> groupResponseList = groupList.stream().map(groupResponse -> {
            if(groupResponse.getCreated_by().equals(emailId)) {
                groupResponse.setHasAdmin(true);
            }
            else {
                groupResponse.setHasAdmin(false);
            }
            return groupResponse;
        }).collect(Collectors.toList());
        return groupResponseList;
    }
}
