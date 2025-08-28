package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.model.User;

public interface UserRepository {
  Optional<User> findByEmail(String email);
  User save(User user);
}
