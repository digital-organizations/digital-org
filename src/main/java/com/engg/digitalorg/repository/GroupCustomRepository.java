package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;

import java.util.List;

public interface GroupCustomRepository {

    List<Group> findAllActiveGroup(Boolean flag);

    void removeCardFromGroup(int cardId);

}
