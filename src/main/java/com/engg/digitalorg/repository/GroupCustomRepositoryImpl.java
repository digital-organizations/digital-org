package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class GroupCustomRepositoryImpl implements GroupCustomRepository{

    @PersistenceContext
    EntityManager entityManager;

    public List<Group> findAllActiveGroup(Boolean flag) {

        Query query = entityManager.createNativeQuery("SELECT gp.* FROM digital.group as gp WHERE gp.active = ?", Group.class);
        query.setParameter(1, flag);
        return query.getResultList();
    }

}
