package com.dhananjaya.Job.Portal.repository;

import com.dhananjaya.Job.Portal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    //find all companies created by a specific user
    List<Company> findByUserId(Long userId);
}