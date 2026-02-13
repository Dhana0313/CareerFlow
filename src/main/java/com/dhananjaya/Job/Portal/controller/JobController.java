package com.dhananjaya.Job.Portal.controller;

import com.dhananjaya.Job.Portal.dto.auth.MessageResponse;
import com.dhananjaya.Job.Portal.dto.job.JobPostRequest;
import com.dhananjaya.Job.Portal.dto.job.JobPostResponse;
import com.dhananjaya.Job.Portal.model.enums.JobLocation;
import com.dhananjaya.Job.Portal.model.enums.JobType;
import com.dhananjaya.Job.Portal.security.services.UserDetailsImpl;
import com.dhananjaya.Job.Portal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYER')") // Only Employers can post
    public ResponseEntity<?> createJobPost(
            @Valid @RequestBody JobPostRequest jobPostRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        
        // Pass the request and the user's ID to the service
        jobService.createJobPost(jobPostRequest, userDetails.getId());

        return ResponseEntity.ok(new MessageResponse("Job posted successfully!"));
    }

    @GetMapping
    // No @PreAuthorize means authenticated users (Candidates/Employers) can access this.
    // (If you want guests to see it, we need to update WebSecurityConfig)
    public ResponseEntity<List<JobPostResponse>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobPostResponse>> searchJobs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) JobType type,
            @RequestParam(required = false) JobLocation locationType) {
            
        return ResponseEntity.ok(jobService.searchJobs(keyword, location, type, locationType));
    }
}