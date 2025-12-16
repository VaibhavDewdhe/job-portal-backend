package com.web.tech.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key for the post

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employer_id", referencedColumnName = "employer_id", nullable = false)
    private EmployerInfo employerInfo; // Link to the EmployerInfo entity
    
    @Override
	public String toString() {
		return "Post [id=" + id + ", employerInfo=" + employerInfo + ", jobTitle=" + jobTitle + ", jobLocation="
				+ jobLocation + ", description=" + description + ", salary=" + salary + ", jobType=" + jobType
				+ ", skillsRequired=" + skillsRequired + ", createdAt=" + createdAt + "]";
	}

	@Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "job_location", nullable = false)
    private String jobLocation;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "job_type", nullable = false)
    private String jobType;

    @Column(name = "skills_required", nullable = false)
    private String skillsRequired;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Default constructor
    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor with fields
    public Post(String jobTitle, String jobLocation, String description, double salary, String jobType,
                String skillsRequired, EmployerInfo employerInfo) {
        this.jobTitle = jobTitle;
        this.jobLocation = jobLocation;
        this.description = description;
        this.salary = salary;
        this.jobType = jobType;
        this.skillsRequired = skillsRequired;
        this.createdAt = LocalDateTime.now();
        this.employerInfo = employerInfo;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployerInfo getEmployerInfo() {
        return employerInfo;
    }

    public void setEmployerInfo(EmployerInfo employerInfo) {
        this.employerInfo = employerInfo;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    // Helper method to get company name from employer info
    public String getCompanyName() {
        return employerInfo != null ? employerInfo.getCompanyName() : null;
    }
}
