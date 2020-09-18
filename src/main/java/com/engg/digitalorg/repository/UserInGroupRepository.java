package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.UserInGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * The interface User in group repository.
 */
@Transactional
public interface UserInGroupRepository extends JpaRepository<UserInGroup, Integer>, UserInGroupCustomRepository {

    /**
     * Delete userfrom group.
     *
     * @param email the email
     */
    @Modifying(clearAutomatically = true)
    @Query("DELETE FROM UserInGroup as uig WHERE uig.email = :email")
    void deleteUserfromGroup( @Param("email") String email);

}
