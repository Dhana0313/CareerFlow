package com.dhananjaya.Job.Portal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String address;
    private String website;
    private String email;

    // Link to the User (Employer) who owns this company
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // One Company can have many Jobs
    // @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<JobPost> jobs = new ArrayList<>();

    public Company(String name, String description, String address, String website, String email, User user) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.website = website;
        this.email = email;
        this.user = user;
    }
}