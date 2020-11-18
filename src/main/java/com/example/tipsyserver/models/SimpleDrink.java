package com.example.tipsyserver.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drinks")
public class SimpleDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer drinkId;
    private String drinkName;
    private String imageUrl;


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
