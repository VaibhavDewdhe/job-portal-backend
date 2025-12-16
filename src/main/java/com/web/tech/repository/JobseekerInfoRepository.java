package com.web.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.tech.model.JobseekerInfo;

import jakarta.transaction.Transactional;

import java.util.List;

public interface JobseekerInfoRepository extends JpaRepository<JobseekerInfo, Long> {
    // Retrieve all JobseekerInfo records by userId
    List<JobseekerInfo> findByUserId(Long userId);

    // Retrieve the first JobseekerInfo record by userId
    JobseekerInfo findFirstByUserId(Long userId);

    // Update profile picture by jobseeker_id
    @Modifying
    @Transactional
    @Query("UPDATE JobseekerInfo j SET j.profilePicture = :profile WHERE j.jobseekerId = :userid")
    int updateProfileByUserId(@Param("profile") String profile, @Param("userid") Long userid);
}
