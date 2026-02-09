package com.dhananjaya.Job.Portal.controller;

import com.dhananjaya.Job.Portal.dto.auth.MessageResponse;
import com.dhananjaya.Job.Portal.dto.company.CompanyRequest;
import com.dhananjaya.Job.Portal.dto.company.CompanyResponse;
import com.dhananjaya.Job.Portal.model.Company;
import com.dhananjaya.Job.Portal.security.services.UserDetailsImpl;
import com.dhananjaya.Job.Portal.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<?> createCompany(
            @Valid @RequestBody CompanyRequest companyRequest,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 1. Pass the User ID from the secure token to the service
        companyService.createCompany(companyRequest, userDetails.getId());

        return ResponseEntity.ok(new MessageResponse("Company created successfully!"));
    }

    @GetMapping("/my-companies")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<List<CompanyResponse>> getMyCompanies(
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<CompanyResponse> companies = companyService.getCompaniesByUser(userDetails.getId());

        return ResponseEntity.ok(companies);
    }
}