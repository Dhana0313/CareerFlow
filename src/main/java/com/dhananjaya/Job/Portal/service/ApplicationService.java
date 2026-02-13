package com.dhananjaya.Job.Portal.service;

import com.dhananjaya.Job.Portal.dto.application.ApplicationRequest;
import com.dhananjaya.Job.Portal.dto.application.ApplicationResponse;
import com.dhananjaya.Job.Portal.model.Application;
import com.dhananjaya.Job.Portal.model.JobPost;
import com.dhananjaya.Job.Portal.model.User;
import com.dhananjaya.Job.Portal.repository.ApplicationRepository;
import com.dhananjaya.Job.Portal.repository.JobPostRepository;
import com.dhananjaya.Job.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private UserRepository userRepository;

    // 1. Candidate applies for a Job
    public void applyForJob(Long userId, ApplicationRequest request) {
        
        // Check if Job exists
        JobPost jobPost = jobPostRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Check for Duplicate Application
        if (applicationRepository.existsByApplicantIdAndJobPostId(userId, request.getJobId())) {
            throw new RuntimeException("You have already applied for this job!");
        }

        User candidate = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Application application = Application.builder()
                .jobPost(jobPost)
                .applicant(candidate)
                .resumeUrl(request.getResumeUrl())
                .coverLetter(request.getCoverLetter())
                .build(); // status defaults to PENDING

        applicationRepository.save(application);
    }

    // 2. Candidate views "My Applications"
    public List<ApplicationResponse> getMyApplications(Long userId) {
        List<Application> applications = applicationRepository.findByApplicantId(userId);
        return applications.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // 3. Employer views "Who applied to my job?"
    public List<ApplicationResponse> getApplicationsForJob(Long jobId, Long employerId) {
        JobPost job = jobPostRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // SECURITY CHECK: Does this employer own the company that posted this job?
        if (!job.getCompany().getUser().getId().equals(employerId)) {
            throw new RuntimeException("Access Denied: You do not own this job post.");
        }

        List<Application> applications = applicationRepository.findByJobPostId(jobId);
        return applications.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // Helper Mapper
    private ApplicationResponse mapToResponse(Application app) {
        return new ApplicationResponse(
                app.getId(),
                app.getJobPost().getId(),
                app.getJobPost().getTitle(),
                app.getJobPost().getCompany().getName(),
                app.getStatus(),
                app.getAppliedAt(),
                app.getResumeUrl()
        );
    }
}