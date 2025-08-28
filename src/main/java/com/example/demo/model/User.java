package com.example.demo.model;

public class User {
  private Long id;
  private String email;
  private String name;

  public User(Long id, String email, String name) {
    this.id = id;
    this.email = email;
    this.name = name;
  }

  // Getter & Setter
  public Long getId() { return id; }
  public String getEmail() { return email; }
  public String getName() { return name; }

  public void setId(Long id) { this.id = id; }
  public void setEmail(String email) { this.email = email; }
  public void setName(String name) { this.name = name; }  
}
