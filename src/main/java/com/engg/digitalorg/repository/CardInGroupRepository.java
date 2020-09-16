package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.CardInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * The interface Card in group repository.
 */
@Transactional
public interface CardInGroupRepository extends JpaRepository<CardInGroup, Integer> {

    /**
     * Delete card in group.
     *
     * @param card_id the card id
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM CardInGroup as cig WHERE cig.card_id = :card_id")
    void deleteCardInGroup( @Param("card_id") int card_id);

}
