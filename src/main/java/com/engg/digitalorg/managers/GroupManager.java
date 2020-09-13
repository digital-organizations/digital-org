package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.UserInGroup;
import com.engg.digitalorg.model.response.CardResponse;
import com.engg.digitalorg.repository.GroupRepository;
import com.engg.digitalorg.repository.UserInGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }

    public List<Group> findAllActiveGroup() {
        return groupRepository.findAllActiveGroup(true);
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group getGroupById(Integer cardId) {
        Optional<Group> group = groupRepository.findById(cardId);
        return group.get();
    }

    public Group updateGroup(Group group) {
        return groupRepository.save(group);
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
}
