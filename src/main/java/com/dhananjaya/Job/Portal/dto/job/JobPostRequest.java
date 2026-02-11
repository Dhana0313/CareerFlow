package com.dhananjaya.Job.Portal.dto.job;

import com.dhananjaya.Job.Portal.model.enums.JobLocation;
import com.dhananjaya.Job.Portal.model.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JobPostRequest {
    
    @NotBlank(message = "Job title is required")
    private String title;
    
    @NotBlank(message = "Description is required")
    private String description;
    
    private String location; // e.g., "New York, USA"

    @NotNull(message = "Job type is required")
    private JobType type; // FULL_TIME, PART_TIME...

    @NotNull(message = "Location type is required")
    private JobLocation locationType; // REMOTE, ONSITE...
    
    private String salaryRange;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}