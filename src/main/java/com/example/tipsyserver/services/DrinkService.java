package com.example.tipsyserver.services;

import com.example.tipsyserver.models.Drink;
import com.example.tipsyserver.repositories.DrinkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {

    @Autowired
    DrinkRepository drinkRepository;

    public List<Drink> searchForDrink(String drinkName, int offset, int limit) {
        return drinkRepository.searchDrinkName(drinkName, offset, limit);
    }

    public Drink createDrink(Drink drink){
        return drinkRepository.save(drink);
    }

    public Drink getDrinkById(int drinkId){
        return drinkRepository.findById(drinkId).get();
    }

    public void deleteDrink(int drinkId){
        drinkRepository.deleteById(drinkId);
    }

    public Drink updateDrink(int drinkId, Drink drink){
        drink.setDrinkId(drinkId);
        return drinkRepository.save(drink);
    }
}
