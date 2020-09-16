package com.engg.digitalorg.repository;

import com.engg.digitalorg.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

}
