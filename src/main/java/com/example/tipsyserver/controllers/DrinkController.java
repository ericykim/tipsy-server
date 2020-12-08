package com.example.tipsyserver.controllers;

import com.example.tipsyserver.models.SimpleDrink;
import com.example.tipsyserver.services.DrinkService;

import java.util.List;

import com.example.tipsyserver.models.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

    @GetMapping
    public String get() {
        return "We good";
    }

    @GetMapping("/api/v1/drinks/search")
    public List<SimpleDrink> searchForDrink(@RequestParam String drinkName,
                                            @RequestParam(defaultValue = "0", required = false) Integer offset,
                                            @RequestParam(defaultValue = "10", required = false) Integer limit) {
        return drinkService.searchForDrink(drinkName, offset, limit);
    }

    @GetMapping("/api/v1/drinks/{drinkId}")
    public Drink getDrinkById(@PathVariable("drinkId") int drinkId) {
        return drinkService.getDrinkById(drinkId);
    }
}
