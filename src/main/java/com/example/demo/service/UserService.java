package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

public class UserService {
  
  private final UserRepository userRepository;

  public UserService(UserRepository repository) {
    this.userRepository = repository;
  }

  public User registerUser(String email, String name) {
    Optional<User> existingUser = userRepository.findByEmail(email);
    if (existingUser.isPresent()) {
        throw new IllegalArgumentException("Email already exists");
    }
    User user = new User(null, email, name);
    return userRepository.save(user);
  }
}
