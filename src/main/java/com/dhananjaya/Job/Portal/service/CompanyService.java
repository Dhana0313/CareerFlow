package com.dhananjaya.Job.Portal.service;

import com.dhananjaya.Job.Portal.dto.company.CompanyRequest;
import com.dhananjaya.Job.Portal.model.Company;
import com.dhananjaya.Job.Portal.model.User;
import com.dhananjaya.Job.Portal.repository.CompanyRepository;
import com.dhananjaya.Job.Portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Company> getCompaniesByUser(Long userId) {
        return companyRepository.findByUserId(userId);
    }
}