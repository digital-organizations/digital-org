package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Icon;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * The interface Icon repository.
 */
@Transactional
public interface IconRepository extends JpaRepository<Icon, Integer> {

}
