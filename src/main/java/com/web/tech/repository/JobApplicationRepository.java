package com.web.tech.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.tech.model.JobApplication;
import com.web.tech.model.JobseekerInfo;
import com.web.tech.model.Post;

import jakarta.transaction.Transactional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    boolean existsByJobseekerAndJobPost(JobseekerInfo jobseeker, Post jobPost);

    List<JobApplication> findByJobseekerJobseekerId(Long jobseekerId);

    @Query("SELECT ja.jobPost.id FROM JobApplication ja WHERE ja.jobseeker.jobseekerId = :jobseekerId")
    List<Long> findPostIdsByJobseekerJobseekerId(@Param("jobseekerId") Long jobseekerId);

    // Method to update application status by ID
    @Modifying
    @Transactional
    @Query("UPDATE JobApplication j SET j.status = :status WHERE j.id = :applicationId")
    int updateApplicationStatus(@Param("status") String status, @Param("applicationId") Long applicationId);
    

    @Modifying
    @Transactional
    @Query("UPDATE JobApplication j SET j.status = :status WHERE j.jobPost.id = :postId AND j.jobseeker.jobseekerId = :jobseekerId")
    int updateJobApplicationStatus(@Param("status") String status, @Param("postId") Long postId, @Param("jobseekerId") Long jobseekerId);

}