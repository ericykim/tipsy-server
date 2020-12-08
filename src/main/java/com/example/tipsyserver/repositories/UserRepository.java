package com.example.tipsyserver.repositories;

import com.example.tipsyserver.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

  @Query(value = "SELECT * " +
          "FROM users " +
          "WHERE users.username = :username AND users.password = :password", nativeQuery = true)
  public User checkUserExist(@Param("username") String username,
                             @Param("password") String password);
}
