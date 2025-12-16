package com.web.tech.model;

import jakarta.persistence.*;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jobseeker_id", nullable = false)
    private JobseekerInfo jobseeker;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post jobPost;


    private String status; // Example: "Pending", "Accepted", "Rejected"
    
    // Alias for compatibility with services
    public String getApplicationStatus() {
        return status;
    }
    
    public void setApplicationStatus(String status) {
        this.status = status;
    }

    private String coverLetter;
    private String expectedSalary;
    private String availability;
    private String resume;
    private java.time.LocalDateTime appliedAt;

    // Constructors
    public JobApplication() {}

    public JobApplication(JobseekerInfo jobseeker, Post jobPost, String status) {
        this.jobseeker = jobseeker;
        this.jobPost = jobPost;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobseekerInfo getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(JobseekerInfo jobseeker) {
        this.jobseeker = jobseeker;
    }

    public Post getJobPost() {
        return jobPost;
    }

    public void setJobPost(Post jobPost) {
        this.jobPost = jobPost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public java.time.LocalDateTime getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(java.time.LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }
}
