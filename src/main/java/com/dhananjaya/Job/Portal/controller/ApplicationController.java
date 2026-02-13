package com.dhananjaya.Job.Portal.controller;

import com.dhananjaya.Job.Portal.dto.application.ApplicationRequest;
import com.dhananjaya.Job.Portal.dto.application.ApplicationResponse;
import com.dhananjaya.Job.Portal.dto.auth.MessageResponse;
import com.dhananjaya.Job.Portal.security.services.UserDetailsImpl;
import com.dhananjaya.Job.Portal.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // 1. Candidate: Apply for a Job
    @PostMapping
    public ResponseEntity<?> applyForJob(
            @Valid @RequestBody ApplicationRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        applicationService.applyForJob(userDetails.getId(), request);

        return ResponseEntity.ok(new MessageResponse("Application submitted successfully!"));
    }

    // 2. Candidate: View "My Applications"
    @GetMapping("/my-applications")
    public ResponseEntity<List<ApplicationResponse>> getMyApplications(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<ApplicationResponse> applications = applicationService.getMyApplications(userDetails.getId());
        return ResponseEntity.ok(applications);
    }

    // 3. Employer: View "Who applied to my job?"
    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasRole('EMPLOYER')") // Security: Only Employers allowed
    public ResponseEntity<List<ApplicationResponse>> getApplicantsForJob(
            @PathVariable Long jobId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<ApplicationResponse> applications = applicationService.getApplicationsForJob(jobId, userDetails.getId());
        return ResponseEntity.ok(applications);
    }
}