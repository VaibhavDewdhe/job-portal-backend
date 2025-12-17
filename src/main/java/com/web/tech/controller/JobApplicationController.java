package com.web.tech.controller;

import com.web.tech.dto.JobApplicationDetailsDto;
import com.web.tech.model.JobApplication;
import com.web.tech.repository.JobApplicationRepository;
import com.web.tech.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/jobapplications")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend access
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @PostMapping("/apply")
    public ResponseEntity<String> applyForJob(
            @RequestParam Long jobseekerId,
            @RequestParam Long postId,
            @RequestParam(required = false) String coverLetter,
            @RequestParam(required = false) String expectedSalary,
            @RequestParam(required = false) String availability
    ) {
        try {
            JobApplication savedApplication = jobApplicationService.applyForJob(jobseekerId, postId, coverLetter, expectedSalary, availability);
            if (savedApplication == null) {
                return ResponseEntity.ok("You have already applied for this job.");
            }
            return ResponseEntity.ok("Job application submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error submitting application: " + e.getMessage());
        }
    }

    @GetMapping("/jobseeker/{jobseekerId}")
    public List<JobApplicationDetailsDto> getApplicationsByJobseeker(@PathVariable Long jobseekerId) {
        List<JobApplicationDetailsDto> applicationDetails = jobApplicationService.getJobseekerApplications(jobseekerId);
        Collections.reverse(applicationDetails);
        return applicationDetails;
    }

    @GetMapping("/jobseeker/{jobseekerId}/applied-posts")
    public List<Long> getAppliedPostIds(@PathVariable Long jobseekerId) {
        return jobApplicationRepository.findPostIdsByJobseekerJobseekerId(jobseekerId);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateApplicationStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        jobApplicationService.updateApplicationStatus(id, request.get("status"));
        return ResponseEntity.ok("Status updated successfully");
    }

    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobApplicationDetailsDto>> getApplicationsForEmployer(@PathVariable Long employerId) {
        List<JobApplicationDetailsDto> applications = jobApplicationService.getEmployerApplications(employerId);
        return ResponseEntity.ok(applications);
    }
}
