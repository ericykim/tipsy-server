package com.example.tipsyserver.services;

import com.example.tipsyserver.models.SimpleDrink;
import com.example.tipsyserver.models.User;
import com.example.tipsyserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

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

    @Transactional
    public SimpleDrink likeDrink(Integer userId, SimpleDrink drink) {
        String queryString = "INSERT INTO users_liked_drinks VALUES (?, ?)";
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, userId);
        query.setParameter(2, drink.getDrinkId());
        query.executeUpdate();
        return drink;
    }

    public Set<SimpleDrink> getLikedDrinks(Integer userId) {
        User user = userRepository.findById(userId).get();
        return user.getLikedDrinks();
    }

    @Transactional
    public void unLikeDrink(Integer userId, Integer drinkId) {
        String queryString = "DELETE FROM users_liked_drinks uld WHERE uld.likes_id = ? AND uld.liked_drinks_id = ?";
        Query query = em.createNativeQuery(queryString);
        query.setParameter(1, userId);
        query.setParameter(2, drinkId);
        query.executeUpdate();
    }

}
