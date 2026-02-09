package com.dhananjaya.Job.Portal.dto.company;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyRequest {
    @NotBlank(message = "Company name cannot be empty")
    private String name;

    private String description;

    @NotBlank(message = "Address is required")
    private String address;

    private String website;
    private String email;
}