package com.dhananjaya.Job.Portal.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String website;
    private String email;
    private Long userId; // Just send the ID, not the whole User object!
}