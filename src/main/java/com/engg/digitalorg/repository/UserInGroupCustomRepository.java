package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.UserInGroup;

import java.util.List;

public interface UserInGroupCustomRepository {

    List<UserInGroup> findAllbyGroup(String groupName);
}
