package com.dhananjaya.Job.Portal.repository;

import com.dhananjaya.Job.Portal.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    
    // For Candidates: "My Applications"
    List<Application> findByApplicantId(Long userId);
    
    // For Employers: "Who applied to this specific job?"
    List<Application> findByJobPostId(Long jobId);
    
    // Check duplication: "Has User X already applied to Job Y?"
    boolean existsByApplicantIdAndJobPostId(Long userId, Long jobId);
}