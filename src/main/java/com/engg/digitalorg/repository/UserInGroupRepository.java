package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.UserInGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserInGroupRepository extends JpaRepository<UserInGroup, Integer>, UserInGroupCustomRepository {

}
