package com.dhananjaya.Job.Portal.dto.application;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationRequest {
    
    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotBlank(message = "Resume link is required")
    private String resumeUrl;

    private String coverLetter;
}