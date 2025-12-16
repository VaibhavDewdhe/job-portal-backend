package com.web.tech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.tech.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    
}
