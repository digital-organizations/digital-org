package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.UserInGroup;

import java.util.List;

public interface UserInGroupCustomRepository {

    List<UserInGroup> findAllUserInGroupByGroupName(String groupName);

    List<UserInGroup> findAllUserInGroupByGroupID(int groupId);
}
