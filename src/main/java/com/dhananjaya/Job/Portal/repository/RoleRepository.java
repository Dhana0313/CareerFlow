package com.dhananjaya.Job.Portal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhananjaya.Job.Portal.model.Role;
import com.dhananjaya.Job.Portal.model.enums.ERole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
    
}
