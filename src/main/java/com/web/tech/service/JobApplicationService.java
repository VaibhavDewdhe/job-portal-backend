
package com.web.tech.service;

import com.web.tech.dto.JobApplicationDetailsDto;
import com.web.tech.model.JobApplication;
import com.web.tech.model.JobseekerInfo;
import com.web.tech.model.Post;
import com.web.tech.repository.JobApplicationRepository;
import com.web.tech.repository.JobseekerInfoRepository;
import com.web.tech.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private JobseekerInfoRepository jobseekerRepository;

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public JobApplication applyForJob(Long jobseekerId, Long postId, String coverLetter, String expectedSalary, String availability) {
        Optional<JobseekerInfo> jobseekerOpt = jobseekerRepository.findById(jobseekerId);
        Optional<Post> postOpt = postRepository.findById(postId);

        if (jobseekerOpt.isPresent() && postOpt.isPresent()) {
            JobseekerInfo jobseeker = jobseekerOpt.get();
            Post post = postOpt.get();

            if (jobApplicationRepository.existsByJobseekerAndJobPost(jobseeker, post)) {
                return null; // Already applied
            }

            JobApplication jobApplication = new JobApplication(jobseeker, post, "Applied");
            jobApplication.setCoverLetter(coverLetter);
            jobApplication.setExpectedSalary(expectedSalary);
            jobApplication.setAvailability(availability);
            jobApplication.setAppliedAt(java.time.LocalDateTime.now());
            return jobApplicationRepository.save(jobApplication);
        }
        throw new RuntimeException("Invalid Jobseeker or Post ID");
    }

    public Optional<JobApplication> getJobApplicationById(Long id) {
        return jobApplicationRepository.findById(id);
    }

    public JobApplication saveJobApplication(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    @Transactional(readOnly = true)
    public List<JobApplicationDetailsDto> getJobseekerApplications(Long jobseekerId) {
        List<JobApplication> applications = jobApplicationRepository.findByJobseekerJobseekerId(jobseekerId);
        return applications.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<JobApplicationDetailsDto> getEmployerApplications(Long employerId) {
        List<JobApplication> applications = jobApplicationRepository.findAll();
        
        return applications.stream()
            .filter(app -> app.getJobPost() != null && 
                          app.getJobPost().getEmployerInfo() != null &&
                          employerId.equals(app.getJobPost().getEmployerInfo().getEmployer_id()))
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public void updateApplicationStatus(Long applicationId, String status) {
        JobApplication application = jobApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        jobApplicationRepository.save(application);
    }

    private JobApplicationDetailsDto mapToDto(JobApplication app) {
        JobApplicationDetailsDto dto = new JobApplicationDetailsDto();
        dto.setApplicationId(app.getId());
        dto.setApplicationStatus(app.getStatus());
        dto.setAppliedAt(app.getAppliedAt());
        dto.setCoverLetter(app.getCoverLetter());
        dto.setExpectedSalary(app.getExpectedSalary());
        dto.setAvailability(app.getAvailability());

        if (app.getJobPost() != null) {
            Post post = app.getJobPost();
            dto.setPostId(post.getId());
            dto.setJobTitle(post.getJobTitle());
            if (post.getEmployerInfo() != null) {
                dto.setCompanyName(post.getEmployerInfo().getCompanyName());
                dto.setEmployerId(post.getEmployerInfo().getEmployer_id());
            }
        }

        if (app.getJobseeker() != null) {
            JobseekerInfo jobseeker = app.getJobseeker();
            dto.setJobseekerId(jobseeker.getJobseekerId());
            dto.setApplicantName(jobseeker.getFullName());
            dto.setApplicantEmail(jobseeker.getEmail());
        }
        
        return dto;
    }
}


