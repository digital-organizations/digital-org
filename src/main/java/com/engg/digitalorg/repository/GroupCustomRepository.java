package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupCustomRepository {

    List<Group> findAllActiveGroup(Boolean flag);

}
