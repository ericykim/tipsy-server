package com.example.tipsyserver.repositories;

import java.util.List;

import com.example.tipsyserver.models.Drink;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface DrinkRepository extends CrudRepository<Drink, Integer> {

    @Query(value = "SELECT * FROM drinks WHERE drinks.drink_name LIKE %:drinkName% ORDER BY drink_name LIMIT :limit OFFSET :offset", nativeQuery =true)
    public List<Drink> searchDrinkName(@Param("drinkName") String drinkName, @Param("offset") int offset,
            @Param("limit") int limit);
}
