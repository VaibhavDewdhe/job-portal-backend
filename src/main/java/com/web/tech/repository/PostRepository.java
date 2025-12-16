package com.web.tech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.tech.model.EmployeePostDTO;
import com.web.tech.model.EmployerInfo;
import com.web.tech.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    // You can add custom queries here if needed, for example:
    // List<Post> findByUserId(Long userId);
	@Query("SELECT p FROM Post p JOIN FETCH p.employerInfo")
    List<Post> findAllPostsWithEmployerInfo();

    List<Post> findByEmployerInfo(EmployerInfo employerInfo);
	
}
