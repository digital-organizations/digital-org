package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.Card;
import com.engg.digitalorg.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
