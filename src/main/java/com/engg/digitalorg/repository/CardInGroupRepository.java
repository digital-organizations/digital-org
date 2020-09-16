package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.CardInGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CardInGroupRepository extends JpaRepository<CardInGroup, Integer> {

}
