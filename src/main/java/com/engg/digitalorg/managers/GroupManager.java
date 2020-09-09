package com.engg.digitalorg.managers;

import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.Group;
import com.engg.digitalorg.repository.CardRepository;
import com.engg.digitalorg.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupManager {

    @Autowired
    private GroupRepository groupRepository;

    public Group getGroup(Integer cardId) {
        Optional<Group> card = groupRepository.findById(cardId);
        return card.get();
    }

    public List<Group> getAllGroup() {
        return groupRepository.findAll();
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
}
