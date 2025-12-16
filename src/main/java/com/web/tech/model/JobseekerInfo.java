package com.web.tech.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "jobseeker_info")
public class JobseekerInfo {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobseekerId;

	@Column(nullable = false)
	private Long userId;
	
	// Add email field for jobseeker contact information
	@Column(nullable = false)
	private String email;
	
	private String branch;
	

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	@Column(nullable = false, length = 100)
	private String fullName;

	private LocalDate dob;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Lob
	private String address;

	@Lob
	private String education;

	@Lob
	private String experience;

	private String course;
	

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	@Lob
	private String skills;

	private String portfolioUrl;

	private String profilePicture;
	
	private String resume;

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Column(nullable = false, updatable = false)
	private LocalDate createdAt = LocalDate.now();

	private LocalDate updatedAt = LocalDate.now();

	public Long getJobseekerId() {
		return jobseekerId;
	}

	public void setJobseekerId(Long jobseekerId) {
		this.jobseekerId = jobseekerId;
	}

	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

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

	@Override
	public String toString() {
		return "JobseekerInfo [jobseekerId=" + jobseekerId + ", userId=" + userId + ", fullName=" + fullName + ", dob="
				+ dob + ", gender=" + gender + ", address=" + address + ", education=" + education + ", experience="
				+ experience + ", skills=" + skills + ", portfolioUrl=" + portfolioUrl + ", profilePicture="
				+ profilePicture + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}

	public enum Gender {
		MALE, FEMALE, OTHER
	}
}
