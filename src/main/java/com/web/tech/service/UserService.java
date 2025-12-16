package com.web.tech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.tech.model.User;
import com.web.tech.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return repository.findById(id);
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
