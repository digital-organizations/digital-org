package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Group;
import com.engg.digitalorg.model.entity.SuggestionQueue;
import com.engg.digitalorg.model.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Collection;

/**
 * The interface Group repository.
 */
@Transactional
public interface SuggestionQueueRepository extends JpaRepository<SuggestionQueue, Integer> {

    /**
     * Gets all suggestion queue by card id.
     *
     * @param card_id the card id
     * @return the all suggestion queue by card id
     */
    @Modifying(clearAutomatically = true)
    @Query("SELECT sq FROM SuggestionQueue sq WHERE sq.card_id = :card_id")
    Collection<SuggestionQueue> getAllSuggestionQueueByCardId(@Param("card_id") int card_id);

    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM SuggestionQueue sq WHERE sq.card_id = :card_id")
    void deleteSuggestionQueueByCardId( @Param("card_id") int card_id);

}
