package com.dhananjaya.Job.Portal.service;

import com.dhananjaya.Job.Portal.dto.job.JobPostRequest;
import com.dhananjaya.Job.Portal.dto.job.JobPostResponse;
import com.dhananjaya.Job.Portal.model.Company;
import com.dhananjaya.Job.Portal.model.JobPost;
import com.dhananjaya.Job.Portal.model.User;
import com.dhananjaya.Job.Portal.repository.CompanyRepository;
import com.dhananjaya.Job.Portal.repository.JobPostRepository;
import com.dhananjaya.Job.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    public void createJobPost(JobPostRequest request, Long userId) {
        // 1. Fetch the Company
        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Error: Company not found"));

        // 2. SECURITY CHECK: Does this user own this company?
        if (!company.getUser().getId().equals(userId)) {
            throw new RuntimeException("Error: You are not authorized to post jobs for this company.");
        }

        // 3. Fetch the User (Employer)
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found"));

        // 4. Create JobPost
        JobPost jobPost = JobPost.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .type(request.getType())
                .locationType(request.getLocationType())
                .salaryRange(request.getSalaryRange())
                .company(company)
                .postedBy(user)
                .build(); // isActive defaults to true thanks to @Builder.Default

        jobPostRepository.save(jobPost);
    }

    public List<JobPostResponse> getAllJobs() {
        List<JobPost> jobs = jobPostRepository.findByIsActiveTrue();
        
        return jobs.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    // Helper method to convert Entity -> DTO
    private JobPostResponse mapToResponse(JobPost job) {
        return new JobPostResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getLocation(),
                job.getType(),
                job.getLocationType(),
                job.getSalaryRange(),
                job.getPostedAt(),
                job.isActive(),
                job.getCompany().getId(),
                job.getCompany().getName()
        );
    }
}