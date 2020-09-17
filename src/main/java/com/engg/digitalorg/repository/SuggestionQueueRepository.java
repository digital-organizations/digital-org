package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.SuggestionQueue;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * The interface Group repository.
 */
@Transactional
public interface SuggestionQueueRepository extends JpaRepository<SuggestionQueue, Integer> {

}
