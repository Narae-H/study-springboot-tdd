package com.example.demo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

public class UserServiceTest {

  private UserRepository userRepository;
  private UserService userService;

  @BeforeEach
  void setUp() {
      userRepository = mock(UserRepository.class);
      userService = new UserService(userRepository);
  }

  @Test
  void registerUser_success() {
      String email = "test@example.com";
      String name = "Narae";

      when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
      when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

      User result = userService.registerUser(email, name);

      assertNotNull(result);
      assertEquals(email, result.getEmail());
      assertEquals(name, result.getName());
      verify(userRepository).findByEmail(email);
      verify(userRepository).save(any(User.class));
  }

  @Test
  void registerUser_emailExists_throwsException() {
      String email = "test@example.com";
      String name = "Narae";

      when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User(1L, email, name)));

      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
          userService.registerUser(email, name);
      });

      assertEquals("Email already exists", exception.getMessage());
      verify(userRepository).findByEmail(email);
      verify(userRepository, never()).save(any(User.class));
  }  
}
