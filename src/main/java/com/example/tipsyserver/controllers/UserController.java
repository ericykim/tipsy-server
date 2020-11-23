package com.example.tipsyserver.controllers;

import com.example.tipsyserver.models.User;
import com.example.tipsyserver.repositories.UserRepository;
import com.example.tipsyserver.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/api/v1/users/logout")
  public String logout(HttpSession session) {
    return userService.logoutUser(session);
  }

  @PostMapping("/api/v1/users/login")
  public User login(HttpSession session, @RequestBody User user) {
    return userService.loginUser(session, user);
  }

  @PostMapping("/api/v1/users/profile")
  public User profile(HttpSession session) {
    return userService.getProfile(session);
  }

  @PostMapping("/api/v1/users/register")
  public User register(HttpSession session, @RequestBody User user) {
    return userService.registerUser(session, user);
  }

}
