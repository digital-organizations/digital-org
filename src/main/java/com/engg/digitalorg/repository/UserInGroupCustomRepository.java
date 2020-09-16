package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.UserInGroup;

import java.util.List;

/**
 * The interface User in group custom repository.
 */
public interface UserInGroupCustomRepository {

    /**
     * Find all user in group by group name list.
     *
     * @param groupName the group name
     * @return the list
     */
    List<UserInGroup> findAllUserInGroupByGroupName(String groupName);

    /**
     * Find all user in group by group id list.
     *
     * @param groupId the group id
     * @return the list
     */
    List<UserInGroup> findAllUserInGroupByGroupID(int groupId);
}
