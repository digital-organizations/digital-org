package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.CardInGroup;
import com.engg.digitalorg.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;
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

    /**
     * Fetch all card by group id collection.
     *
     * @param group_id the group id
     * @return the collection
     */
    @Modifying(clearAutomatically = true)
    @Query("SELECT cig FROM CardInGroup cig WHERE cig.group_id = :group_id")
    Collection<CardInGroup> fetchAllCardByGroupId(@Param("group_id") int group_id);

    @Modifying(clearAutomatically = true)
    @Query("SELECT cig FROM CardInGroup cig WHERE cig.card_id = :card_id")
    Collection<CardInGroup> fetchGrupByCardId(@Param("card_id") int card_id);
}
