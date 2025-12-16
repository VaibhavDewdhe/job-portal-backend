package com.web.tech.model;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "employer_info")
public class EmployerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employer_id;

    @Column(name = "user_id", nullable = false)
    private Long userId; // Store userId instead of mapping a User entity

    @Column(name = "company_name", nullable = false)
    private String companyName;

    private String industry;
   
    @OneToMany(mappedBy = "employerInfo", fetch = FetchType.LAZY)
    private List<Post> posts;

    private String location;
    private String profilePicture;
    private String portfolioUrl;
	public String getPortfolioUrl() {
		return portfolioUrl;
	}

	public void setPortfolioUrl(String portfolioUrl) {
		this.portfolioUrl = portfolioUrl;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Long getEmployer_id() {
		return employer_id;
	}

	public void setEmployer_id(Long employer_id) {
		this.employer_id = employer_id;
	}

	@Override
	public String toString() {
		return "EmployerInfo [employer_id=" + employer_id + ", userId=" + userId + ", companyName=" + companyName + ", industry="
				+ industry + ", location=" + location + ", openPositions=" + openPositions + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getOpenPositions() {
		return openPositions;
	}

	public void setOpenPositions(Integer openPositions) {
		this.openPositions = openPositions;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "open_positions", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer openPositions = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }
}
