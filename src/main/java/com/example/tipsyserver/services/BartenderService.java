package com.example.tipsyserver.services;

import com.example.tipsyserver.models.Drink;
import com.example.tipsyserver.models.SimpleDrink;
import com.example.tipsyserver.models.User;
import com.example.tipsyserver.repositories.DrinkRepository;
import com.example.tipsyserver.repositories.SimpleDrinkRepository;
import com.example.tipsyserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BartenderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    SimpleDrinkRepository simpleDrinkRepository;

    public Drink createDrink(Integer userId, Drink drink) {
        Drink savedDrink = drinkRepository.save(drink);
        User user = userRepository.findById(userId).get();
        SimpleDrink simpleDrink = simpleDrinkFromDrink(drink);
        user.addCreatedDrink(simpleDrink);
        userRepository.save(user);
        return savedDrink;
    }

    public Set<SimpleDrink> getCreatedDrinks(Integer userId) {
        User user = userRepository.findById(userId).get();
        return user.getCreatedDrinks();
    }

    public void deleteDrink(Integer userId, Integer drinkId){
        User user = userRepository.findById(userId).get();

        Drink fullDrink = drinkRepository.findById(drinkId).get();
        fullDrink.getSteps().clear();
        fullDrink.getIngredients().clear();
        drinkRepository.save(fullDrink);

        SimpleDrink drink = simpleDrinkRepository.findById(drinkId).get();
        user.removeCreatedDrink(drink);
        userRepository.save(user);
    }

    public Drink updateDrink(Integer userId, Integer drinkId, Drink drink){
        User user = userRepository.findById(userId).get();

        SimpleDrink oldDrink = simpleDrinkRepository.findById(drinkId).get();
        user.removeCreatedDrink(oldDrink);

        SimpleDrink simpleDrink = simpleDrinkFromDrink(drink);
        user.addCreatedDrink(simpleDrink);

        userRepository.save(user);
        return drinkRepository.save(drink);
    }

    private SimpleDrink simpleDrinkFromDrink(Drink drink){
        SimpleDrink simpleDrink = new SimpleDrink();
        simpleDrink.setDrinkId(drink.getDrinkId());
        simpleDrink.setDrinkName(drink.getDrinkName());
        simpleDrink.setImageUrl(drink.getImageUrl());
        simpleDrink.setCreatorId(drink.getCreatorId());
        simpleDrink.setCreatorUsername(drink.getCreatorUsername());
        return  simpleDrink;
    }
}
