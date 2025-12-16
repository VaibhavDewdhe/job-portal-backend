package com.web.tech.model;


import java.util.List;

public class JobseekerProfileDTO {
    private Long jobseekerId;
    private String fullName;
    private String education;
    private String experience;
    private String address;
    private List<JobApplicationDTO> applications;

    public JobseekerProfileDTO(Long jobseekerId, String fullName, String education, String experience, String address, List<JobApplicationDTO> applications) {
        this.jobseekerId = jobseekerId;
        this.fullName = fullName;
        this.education = education;
        this.experience = experience;
        this.address = address;
        this.applications = applications;
    }

    public Long getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(Long jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<JobApplicationDTO> getApplications() {
        return applications;
    }

    public void setApplications(List<JobApplicationDTO> applications) {
        this.applications = applications;
    }
}
