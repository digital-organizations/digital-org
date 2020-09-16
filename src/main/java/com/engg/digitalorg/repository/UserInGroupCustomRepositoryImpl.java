package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.UserInGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * The type User in group custom repository.
 */
@Repository
@Transactional(readOnly = true)
public class UserInGroupCustomRepositoryImpl implements UserInGroupCustomRepository {

    /**
     * The Entity manager.
     */
    @PersistenceContext
    EntityManager entityManager;

    public List<UserInGroup> findAllUserInGroupByGroupName(String groupName) {
        Query query = entityManager.createNativeQuery("SELECT uig.* FROM digital.user_in_group as uig WHERE uig.group_id = ?", UserInGroup.class);
        query.setParameter(1, groupName);
        return query.getResultList();
    }

    @Override
    public List<UserInGroup> findAllUserInGroupByGroupID(int groupId) {
        Query query = entityManager.createNativeQuery("SELECT uig.* FROM digital.user_in_group as uig WHERE uig.group_id = ?", UserInGroup.class);
        query.setParameter(1, groupId);
        return query.getResultList();
    }
}
