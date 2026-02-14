package com.dhananjaya.Job.Portal.repository;

import com.dhananjaya.Job.Portal.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Long>, JpaSpecificationExecutor<JobPost> {
    // Find all jobs posted by a specific company
    List<JobPost> findByCompanyId(Long companyId);
    
    // Find all active jobs (for job seekers)
    List<JobPost> findByIsActiveTrue();
}