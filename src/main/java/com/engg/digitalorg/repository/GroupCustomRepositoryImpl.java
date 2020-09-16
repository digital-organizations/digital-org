package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.CardInGroup;
import com.engg.digitalorg.model.entity.Group;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * The type Group custom repository.
 */
@Repository
@Transactional(readOnly = true)
public class GroupCustomRepositoryImpl implements GroupCustomRepository {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    public List<Group> findAllActiveGroup(Boolean flag) {

        Query query = entityManager.createNativeQuery("SELECT gp.* FROM digital.group as gp WHERE gp.active = ?", Group.class);
        query.setParameter(1, flag);
        return query.getResultList();
    }

    @Override
    public void removeCardFromGroup(int cardId) {
        Query query = entityManager.createNativeQuery("DELETE FROM digital.card_in_group as cig WHERE cig.card_id = ?", CardInGroup.class);
        query.setParameter(1, cardId);
        query.getResultList();
    }


}
