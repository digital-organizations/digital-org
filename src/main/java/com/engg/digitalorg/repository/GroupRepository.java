package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * The interface Group repository.
 */
@Transactional
public interface GroupRepository extends JpaRepository<Group, Integer> {

}
