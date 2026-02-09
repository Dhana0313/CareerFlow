package com.dhananjaya.Job.Portal.service;

import com.dhananjaya.Job.Portal.dto.company.CompanyRequest;
import com.dhananjaya.Job.Portal.dto.company.CompanyResponse;
import com.dhananjaya.Job.Portal.model.Company;
import com.dhananjaya.Job.Portal.model.User;
import com.dhananjaya.Job.Portal.repository.CompanyRepository;
import com.dhananjaya.Job.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    public void createCompany(CompanyRequest companyRequest, Long userId) {
        // 1. Fetch the User from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        // 2. Create the Company Object
        Company company = new Company(
                companyRequest.getName(),
                companyRequest.getDescription(),
                companyRequest.getAddress(),
                companyRequest.getWebsite(),
                companyRequest.getEmail(),
                user
        );

        // 3. Save to DB
        companyRepository.save(company);
    }

    public List<CompanyResponse> getCompaniesByUser(Long userId) {
        List<Company> companies = companyRepository.findByUserId(userId);

        // Convert Entity -> DTO
        return companies.stream().map(company -> new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getDescription(),
                company.getAddress(),
                company.getWebsite(),
                company.getEmail(),
                company.getUser().getId() // Safe: Getting ID doesn't trigger lazy loading issues
        )).collect(Collectors.toList());
    }
}