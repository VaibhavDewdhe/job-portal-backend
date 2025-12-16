package com.web.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.web.tech.model.EmployerInfo;
import com.web.tech.model.JobseekerInfo;

import jakarta.transaction.Transactional;

public interface EmployerInfoRepository extends JpaRepository<EmployerInfo, Long> {
	EmployerInfo findByUserId(Long userId);
	
	
	 // Update profile picture by jobseeker_id
    @Modifying
    @Transactional
    @Query("UPDATE EmployerInfo j SET j.profilePicture = :profile WHERE j.employer_id = :userid")
    int updateProfileByUserId(@Param("profile") String profile, @Param("userid") Long userid);
	
    
    EmployerInfo findFirstByUserId(Long userId);

    
}