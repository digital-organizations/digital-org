package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.UserInGroup;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserInGroupCustomRepositoryImpl implements UserInGroupCustomRepository{

    @PersistenceContext
    EntityManager entityManager;

    public List<UserInGroup> findAllbyGroup(String groupName) {
        Query query = entityManager.createNativeQuery("SELECT uig.* FROM digital.user-in-group as uig WHERE uig.group_name = ?", UserInGroup.class);
        query.setParameter(1, groupName);
        return query.getResultList();
    }
}
