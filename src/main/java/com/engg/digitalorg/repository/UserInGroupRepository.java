package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.UserInGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInGroupRepository extends JpaRepository<UserInGroup, Integer>, UserInGroupCustomRepository {


}
