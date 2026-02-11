package com.dhananjaya.Job.Portal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.dhananjaya.Job.Portal.model.enums.JobLocation;
import com.dhananjaya.Job.Portal.model.enums.JobType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private String location; // City, Country (e.g., "Colombo, LK")

    @Enumerated(EnumType.STRING)
    private JobType type; // FULL_TIME, PART_TIME

    @Enumerated(EnumType.STRING)
    private JobLocation locationType; // REMOTE, ONSITE

    private String salaryRange; // e.g., "50k - 80k"

    @Builder.Default
    private boolean isActive = true; // Job is Open by default

    @CreationTimestamp
    private LocalDateTime postedAt;

    // Relationship: A Job belongs to a Company
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // Relationship: A Job was posted by a User (Employer)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User postedBy;
    
}
