package com.example.tipsyserver.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "drinks")
public class SimpleDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer drinkId;
    private String drinkName;
    private String imageUrl;

    @JsonIgnore
    @ManyToMany(mappedBy = "likedDrinks")
    private Set<User> likes;

    @PreRemove
    private void removeLikedDrinksFromUser() {
        for(User u: likes){
            u.getLikedDrinks().remove(this);
        }
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }


}
