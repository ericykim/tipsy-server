package com.example.tipsyserver.repositories;

import com.example.tipsyserver.models.SimpleDrink;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SimpleDrinkRepository extends CrudRepository<SimpleDrink, Integer> {
    @Query(value = "SELECT * FROM drinks WHERE drinks.drink_name LIKE %:drinkName% ORDER BY drink_name LIMIT :limit OFFSET :offset", nativeQuery =true)
    public List<SimpleDrink> searchDrinkName(@Param("drinkName") String drinkName, @Param("offset") int offset,
                                             @Param("limit") int limit);
}
