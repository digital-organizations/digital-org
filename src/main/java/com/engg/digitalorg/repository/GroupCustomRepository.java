package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;

import java.util.List;

/**
 * The interface Group custom repository.
 */
public interface GroupCustomRepository {

    /**
     * Find all active group list.
     *
     * @param flag the flag
     * @return the list
     */
    List<Group> findAllActiveGroup(Boolean flag);

    /**
     * Remove card from group.
     *
     * @param cardId the card id
     */
    void removeCardFromGroup(int cardId);

}
