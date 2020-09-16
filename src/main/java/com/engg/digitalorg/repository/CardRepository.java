package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface CardRepository extends JpaRepository<Card, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Card c SET c.url_id = :url_id, c.icon_id = :icon_id WHERE c.id = :id")
    void updateAddress(@Param("url_id") int url_id, @Param("icon_id") int icon_id,@Param("id") int id);
}
