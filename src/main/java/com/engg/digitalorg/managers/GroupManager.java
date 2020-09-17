package com.engg.digitalorg.managers;

import com.engg.digitalorg.exception.DigitalOrgException;
import com.engg.digitalorg.model.entity.CardInGroup;
import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.UserInGroup;
import com.engg.digitalorg.model.request.CardInGroupRequest;
import com.engg.digitalorg.model.request.GroupRequest;
import com.engg.digitalorg.model.request.GroupUpdateRequest;
import com.engg.digitalorg.model.response.GroupResponse;
import com.engg.digitalorg.repository.CardInGroupRepository;
import com.engg.digitalorg.repository.GroupRepository;
import com.engg.digitalorg.repository.UserInGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * The type Group manager.
 */
@Component
public class GroupManager {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserInGroupRepository userInGroupRepository;

    @Autowired
    private CardInGroupRepository cardInGroupRepository;

    /**
     * Gets group.
     *
     * @param cardId the card id
     * @return the group
     */
    public Group getGroup(Integer cardId) {
        Optional<Group> card = groupRepository.findById(cardId);
        return card.get();
    }

    /**
     * Create group group response.
     *
     * @param groupRequest the group request
     * @return the group response
     */
    public GroupResponse createGroup(GroupRequest groupRequest) {

        ModelMapper modelMapper = new ModelMapper();
        Group groupMapper = modelMapper.map(groupRequest, Group.class);
        groupMapper.setCreated_date(new Date());
        groupMapper.setUpdated_date(new Date());
        groupMapper.setActive(true);

        Group group = groupRepository.save(groupMapper);

        UserInGroup userInGroup = new UserInGroup();
        userInGroup.setGroup_id(group.getId());
        userInGroup.setAdded_by(group.getCreated_by());
        userInGroup.setAdded_date(new Date());
        userInGroup.setEmail(group.getCreated_by());
        userInGroupRepository.save(userInGroup);

        ModelMapper resModelMapper = new ModelMapper();
        GroupResponse groupResponse = resModelMapper.map(group, GroupResponse.class);
        groupResponse.setHasAdmin(true);
        return groupResponse;
    }

    /**
     * Update group atomic reference.
     *
     * @param groupRequest the group request
     * @return the atomic reference
     */
    public AtomicReference<GroupResponse> updateGroup(GroupUpdateRequest groupRequest) {
        AtomicReference<GroupResponse> groupResponse = null;
        Group group = getGroupById(groupRequest.getId());
        if (group == null) {
            throw new DigitalOrgException("Requested Group is not found.");
        }

        userInGroupRepository.findAllUserInGroupByGroupID(group.getId()).stream().map(userInGroup -> {
            if (userInGroup.getEmail().equals(groupRequest.getUpdated_by())) {
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

    /**
     * Gets group by id.
     *
     * @param cardId the card id
     * @return the group by id
     */
    public Group getGroupById(Integer cardId) {
        Optional<Group> group = groupRepository.findById(cardId);
        return group.get();
    }


    /**
     * Add user to group.
     *
     * @param userEmail  the user email
     * @param adminEmail the admin email
     * @param groupId    the group id
     */
    public void addUserToGroup(String userEmail, String adminEmail, int groupId) {
        List<UserInGroup> userInGroups = userInGroupRepository.findAllUserInGroupByGroupID(groupId);
        userInGroups.stream().map(userInGroup -> {
            if (userInGroup.getEmail().equals(adminEmail)) {
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

    /**
     * Remove user to group.
     *
     * @param email   the email
     * @param groupId the group id
     */
    public void removeUserToGroup(String email, int groupId) {
        List<UserInGroup> userInGroups = userInGroupRepository.findAllUserInGroupByGroupID(groupId);
        if (userInGroups.size() > 1) {
            userInGroups.stream().map(userInGroup -> {
                if (userInGroup.getEmail().equals(email)) {
                    userInGroupRepository.deleteById(userInGroup.getId());
                }

                return "";
            });
        } else {
            throw new DigitalOrgException("You are only admin of this group, please other as a admin to this group, then remove");
        }
    }

    /**
     * Gets all group manager.
     *
     * @param emailId the email id
     * @return the all group manager
     */
    public List<GroupResponse> getAllGroupManager(String emailId) {
        List<GroupResponse> groupList = groupRepository.findAll().stream()
                .collect(Collectors.mapping(p -> new ModelMapper().map(p, GroupResponse.class), Collectors.toList()));

        List<GroupResponse> groupResponseList = groupList.stream().map(groupResponse -> {
            if (groupResponse.getCreated_by().equals(emailId)) {
                groupResponse.setHasAdmin(true);
            } else {
                groupResponse.setHasAdmin(false);
            }
            return groupResponse;
        }).collect(Collectors.toList());
        return groupResponseList;
    }

    /**
     * Add card to group manager card in group.
     *
     * @param cardInGroupRequest the card in group request
     * @return the card in group
     */
    public CardInGroup addCardToGroupManager(CardInGroupRequest cardInGroupRequest) {
        Group  group = getGroupById(cardInGroupRequest.getGroup_id());
        Collection<CardInGroup> cardInGroups =  cardInGroupRepository.fetchAllCardByGroupId(group.getId());
        if(cardInGroups.isEmpty()) {
            CardInGroup cardInGroup = saveCardInGroup(cardInGroupRequest);
            return cardInGroup;
        }
        else {
            userInGroupRepository.findAllUserInGroupByGroupID(group.getId()).stream().map(userInGroup -> {
                if (userInGroup.getEmail().equals(cardInGroupRequest.getAdded_by())) {
                    return saveCardInGroup(cardInGroupRequest);
                }
                return null;
            });
        }
        return null;
    }


    private CardInGroup saveCardInGroup(CardInGroupRequest cardInGroupRequest) {
        CardInGroup cardInGroup = new CardInGroup();
        cardInGroup.setCard_id(cardInGroupRequest.getCard_id());
        cardInGroup.setGroup_id(cardInGroupRequest.getCard_id());
        cardInGroup.setAdded_by(cardInGroupRequest.getAdded_by());
        cardInGroup.setAdded_date(new Date());
        cardInGroupRepository.save(cardInGroup);
        return cardInGroup;
    }

    /**
     * Remove card to group manager object.
     *
     * @param cardInGroupRequest the card in group request
     * @return the object
     */
    public Object removeCardToGroupManager(CardInGroupRequest cardInGroupRequest) {
        Group  group = getGroupById(cardInGroupRequest.getGroup_id());
        Collection<CardInGroup> cardInGroups =  cardInGroupRepository.fetchAllCardByGroupId(group.getId());
        if(cardInGroups.isEmpty()) {
            return null;
        }
        else {
            userInGroupRepository.findAllUserInGroupByGroupID(group.getId()).stream().map(userInGroup -> {
                if (userInGroup.getEmail().equals(cardInGroupRequest.getAdded_by())) {
                    cardInGroups.stream().map(cardInGroup -> {
                        cardInGroupRepository.deleteCardInGroup(cardInGroup.getCard_id());
                        return null;
                    });
                }
                return null;
            });
        }
        return null;
    }
}
