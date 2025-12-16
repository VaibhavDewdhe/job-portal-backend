package com.web.tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.tech.model.JobseekerInfo;
import com.web.tech.repository.JobseekerInfoRepository;

@Service
public class JobseekerInfoService {

    private final JobseekerInfoRepository repository;

    public JobseekerInfoService(JobseekerInfoRepository repository) {
        this.repository = repository;
    }

    public JobseekerInfo saveJobseekerInfo(JobseekerInfo jobseekerInfo) {
        return repository.save(jobseekerInfo);
    }

//    public Optional<JobseekerInfo> getJobseekerInfoById(Long id) {
//       return repository.findById(id);
//    }
    
    public JobseekerInfo getInfo(long id)
    {
    	return  repository.findFirstByUserId(id);
    }

    public List<JobseekerInfo> getJobseekerInfoByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<JobseekerInfo> getAllJobseekers() {
        return repository.findAll();
    }

    public void deleteJobseekerInfo(Long id) {
        repository.deleteById(id);
    }
    //-----------------------------------------------------------------------
    
    public JobseekerInfo updateJobseekerInfoByUserId(Long userId, JobseekerInfo updatedJobseekerInfo) {
        // Find jobseeker by userId using the updated method name
        Optional<JobseekerInfo> existingJobseekerInfoList = repository.findById(userId);
       
        if (existingJobseekerInfoList.isEmpty()) {
            throw new RuntimeException("Jobseeker not found with userId: " + userId);
        }
        
        JobseekerInfo existingJobseekerInfo = existingJobseekerInfoList.get();

        // Update fields
        existingJobseekerInfo.setFullName(updatedJobseekerInfo.getFullName());
        existingJobseekerInfo.setDob(updatedJobseekerInfo.getDob());
        existingJobseekerInfo.setGender(updatedJobseekerInfo.getGender());
        existingJobseekerInfo.setAddress(updatedJobseekerInfo.getAddress());
        existingJobseekerInfo.setEducation(updatedJobseekerInfo.getEducation());
        existingJobseekerInfo.setExperience(updatedJobseekerInfo.getExperience());
        existingJobseekerInfo.setSkills(updatedJobseekerInfo.getSkills());
        existingJobseekerInfo.setPortfolioUrl(updatedJobseekerInfo.getPortfolioUrl());
        existingJobseekerInfo.setProfilePicture(updatedJobseekerInfo.getProfilePicture());
        existingJobseekerInfo.setUpdatedAt(updatedJobseekerInfo.getUpdatedAt());
        existingJobseekerInfo.setBranch(updatedJobseekerInfo.getBranch());
        existingJobseekerInfo.setCourse(updatedJobseekerInfo.getCourse());
        // Save and return updated jobseekerInfo
        return repository.save(existingJobseekerInfo);
    }
    
    
    public int updateprofile(String name ,long id)
    {
    return 	repository.updateProfileByUserId(name, id);
    }
    
    
    
    
}
