package com.dhananjaya.Job.Portal.repository.spec;

import com.dhananjaya.Job.Portal.model.JobPost;
import com.dhananjaya.Job.Portal.model.enums.JobLocation;
import com.dhananjaya.Job.Portal.model.enums.JobType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class JobPostSpecification {

    // Filter by Title or Description (Case Insensitive)
    public static Specification<JobPost> hasKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(keyword)) {
                return null; // Ignore this filter if keyword is empty
            }
            String lowerKeyword = "%" + keyword.toLowerCase() + "%";
            
            // Search in Title OR Description
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), lowerKeyword),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), lowerKeyword)
            );
        };
    }

    // Filter by Location (Exact Match)
    public static Specification<JobPost> hasLocation(String location) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(location)) {
                return null;
            }
            // Case-insensitive location search (e.g., "new york" finds "New York")
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), "%" + location.toLowerCase() + "%");
        };
    }

    // Filter by Job Type (FULL_TIME, PART_TIME...)
    public static Specification<JobPost> hasType(JobType type) {
        return (root, query, criteriaBuilder) -> {
            if (type == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("type"), type);
        };
    }
    
    // Filter by Work Mode (REMOTE, ONSITE...)
    public static Specification<JobPost> hasLocationType(JobLocation locationType) {
        return (root, query, criteriaBuilder) -> {
            if (locationType == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("locationType"), locationType);
        };
    }

    // Filter: "Show me jobs that pay AT LEAST this much"
    public static Specification<JobPost> hasSalary(Double minPay) {
        return (root, query, criteriaBuilder) -> {
            if (minPay == null) {
                return null;
            }
            // Logic: Job's minSalary >= User's minPay
            // Example: User wants 60k. Job pays 50k-80k. 
            // Matches because 80k > 60k? Or strict match?
            
            // Standard Logic: "Show jobs where max budget is within range"
            return criteriaBuilder.greaterThanOrEqualTo(root.get("maxSalary"), minPay);
        };
    }
}