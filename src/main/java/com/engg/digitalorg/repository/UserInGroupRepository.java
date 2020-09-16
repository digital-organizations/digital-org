package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.UserInGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * The interface User in group repository.
 */
@Transactional
public interface UserInGroupRepository extends JpaRepository<UserInGroup, Integer>, UserInGroupCustomRepository {

}
