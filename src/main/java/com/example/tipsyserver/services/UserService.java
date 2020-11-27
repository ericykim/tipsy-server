package com.example.tipsyserver.services;

import com.example.tipsyserver.models.User;
import com.example.tipsyserver.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public String logoutUser(HttpSession session) {
    session.removeAttribute("profile");
    return "Logout Success";
  }

  public User loginUser(HttpSession session, User user) {
    User existingUser = userRepository.checkUserExist(user.getUsername(), user.getPassword());

    existingUser.setPassword(" ");
    session.setAttribute("profile", existingUser);
    return existingUser;
  }

  public User getProfile(HttpSession session) {
    User profile = (User) session.getAttribute("profile");
    return profile;
  }

  public User registerUser(HttpSession session, User user) {
    User newUser = userRepository.save(user);
    newUser.setPassword(" ");
    session.setAttribute("profile", newUser);
    return newUser;
  }

}
