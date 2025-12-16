package com.web.tech.controller;

import com.web.tech.model.LoginRequest;
import com.web.tech.model.User;
import com.web.tech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000/")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Find user by username
  
    	Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername	(loginRequest.getUsername()));
        if (userOptional.isEmpty()) {
            // Handle the case where the user is not found
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        User user = userOptional.get();
        System.err.print(user);
        // Check plain-text password
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            // Handle the case where the password does not match
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        // Return user details on successful login
        System.err.println(user);
        return ResponseEntity.ok(user);
    }
}
