package com.dhananjaya.Job.Portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhananjaya.Job.Portal.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    
}
