package com.web.tech.model;


public class EmployeePostDTO {
    private Long id;
    private String createdAt;
    private String description;
    private String jobLocation;
    private String jobTitle;
    private String jobType;
    private double salary;
    private String skillsRequired;

    private String companyName;
    private String industry;
    private String location;
    private String profilePicture;
    private String portfolioUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJobLocation() {
		return jobLocation;
	}
	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getSkillsRequired() {
		return skillsRequired;
	}
	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
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
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getPortfolioUrl() {
		return portfolioUrl;
	}
	public void setPortfolioUrl(String portfolioUrl) {
		this.portfolioUrl = portfolioUrl;
	}
	@Override
	public String toString() {
		return "EmployeePostDTO [id=" + id + ", createdAt=" + createdAt + ", description=" + description
				+ ", jobLocation=" + jobLocation + ", jobTitle=" + jobTitle + ", jobType=" + jobType + ", salary="
				+ salary + ", skillsRequired=" + skillsRequired + ", companyName=" + companyName + ", industry="
				+ industry + ", location=" + location + ", profilePicture=" + profilePicture + ", portfolioUrl="
				+ portfolioUrl + "]";
	}
    

    
    
}
