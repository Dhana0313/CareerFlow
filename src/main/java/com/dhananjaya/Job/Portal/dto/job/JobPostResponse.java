package com.dhananjaya.Job.Portal.dto.job;

import com.dhananjaya.Job.Portal.model.enums.JobLocation;
import com.dhananjaya.Job.Portal.model.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private JobType type;
    private JobLocation locationType;
    private Double minSalary;
    private Double maxSalary;
    private LocalDateTime postedAt;
    private boolean isActive;
    
    // Flattened Company Info
    private Long companyId;
    private String companyName;
}