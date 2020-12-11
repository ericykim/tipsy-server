package com.example.tipsyserver.controllers;

import com.example.tipsyserver.models.Drink;
import com.example.tipsyserver.models.SimpleDrink;
import com.example.tipsyserver.models.User;
import com.example.tipsyserver.services.BartenderService;
import com.example.tipsyserver.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
public class UserController {

  @Autowired
  UserService userService;

  @Autowired
  BartenderService bartenderService;

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

  @GetMapping("/api/v1/users/{userId}")
  public User getUserById(@PathVariable("userId") Integer userId) {
    return userService.getUserById(userId);
  }


  /*endpoints for liked drinks*/

  @GetMapping("/api/v1/users/{userId}/likedDrinks")
  public Set<SimpleDrink> getLikedDrinks(@PathVariable("userId") Integer userId) {
    return userService.getLikedDrinks(userId);
  }

  @PostMapping("api/v1/users/{userId}/likedDrinks")
  public SimpleDrink likeDrink(@PathVariable("userId") Integer userId, @RequestBody SimpleDrink drink){
    return userService.likeDrink(userId, drink);
  }

  @DeleteMapping("api/v1/users/{userId}/likedDrinks/{drinkId}")
  public void unLikeDrink(@PathVariable("userId") Integer userId, @PathVariable("drinkId") Integer drinkId) {
    userService.unLikeDrink(userId, drinkId);
  }

  /*endpoints for created drinks*/

  @GetMapping("api/v1/users/{userId}/createdDrinks")
  public Set<SimpleDrink> getCreatedDrinks(@PathVariable("userId") Integer userId) {
    return bartenderService.getCreatedDrinks(userId);
  }
  @PostMapping("api/v1/users/{userId}/createdDrinks")
  public Drink createDrink(@PathVariable("userId") Integer userId, @RequestBody Drink drink) {
    return bartenderService.createDrink(userId, drink);
  }
  @DeleteMapping("api/v1/users/{userId}/createdDrinks/{drinkId}")
  public void deleteCreatedDrink(@PathVariable("userId") Integer userId, @PathVariable("drinkId") Integer drinkId) {
    bartenderService.deleteDrink(userId, drinkId);
  }

  @PutMapping("api/v1/users/{userId}/createdDrinks/{drinkId}")
  public Drink updateDrink(@PathVariable Integer userId, @PathVariable Integer drinkId, @RequestBody Drink drink){
    return bartenderService.updateDrink(userId, drinkId, drink);
  }

}
