package com.dhananjaya.Job.Portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhananjaya.Job.Portal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    
}
