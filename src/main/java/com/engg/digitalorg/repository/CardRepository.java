package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
