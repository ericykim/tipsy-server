package com.example.tipsyserver.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String username;
    private String password;
    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany(targetEntity = SimpleDrink.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SimpleDrink> likedDrinks = new HashSet<>();

    @OneToMany(targetEntity = SimpleDrink.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<SimpleDrink> createdDrinks = new HashSet<>();

    public void addCreatedDrink(SimpleDrink drink) {
        this.createdDrinks.add(drink);
    }

    public void removeCreatedDrink(SimpleDrink drink) {
        createdDrinks.remove(drink);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<SimpleDrink> getCreatedDrinks() {
        return createdDrinks;
    }

    public void setCreatedDrinks(Set<SimpleDrink> createdDrinks) {
        this.createdDrinks = createdDrinks;
    }

    public Set<SimpleDrink> getLikedDrinks() {
        return likedDrinks;
    }

    public void setLikedDrinks(Set<SimpleDrink> likedDrinks) {
        this.likedDrinks = likedDrinks;
    }
}
