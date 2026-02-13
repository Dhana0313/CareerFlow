package com.dhananjaya.Job.Portal.dto.application;

import com.dhananjaya.Job.Portal.model.enums.ApplicationStatus; // <--- Now it IS used!
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse {
    private Long id;
    private Long jobId;
    private String jobTitle;
    private String companyName;
    
    private ApplicationStatus status; 
    
    private LocalDateTime appliedAt;
    private String resumeUrl;
}