package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.UserInGroup;
import com.engg.digitalorg.model.mapper.StringListConverter;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.response.GroupResponse;
import com.engg.digitalorg.repository.GroupRepository;
import com.engg.digitalorg.repository.UserInGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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


    public List<Group> findAllActiveGroup() {
        return groupRepository.findAllActiveGroup(true);
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

        StringListConverter stringListConverter = new StringListConverter();
        groupResponse.setAdmin(stringListConverter.convertToEntityAttribute(group.getAdmin()));
        return groupResponse;
    }

    public Group getGroupById(Integer cardId) {
        Optional<Group> group = groupRepository.findById(cardId);
        return group.get();
    }

    public void deleteGroup(Integer groupId) {
        groupRepository.deleteById(groupId);
    }

    public void addUserToGroup(String user, String groupName, String admin) {
        UserInGroup userInGroup = new UserInGroup(user, groupName, admin);
        userInGroup.setAdded_date(new Date());
        userInGroupRepository.save(userInGroup);
    }

    public void removeUserToGroup(String user, String groupName, String admin) {
        userInGroupRepository.findAllbyGroup(groupName).stream().map(userInGroup -> {
            if(userInGroup.getEmail().equals(user)) {
                userInGroupRepository.deleteById(userInGroup.getId());
            }

            return "";
        });
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
            groupResponse.setAdmin(new ArrayList<>());
            return groupResponse;
        }).collect(Collectors.toList());
        return groupResponseList;
    }
}
