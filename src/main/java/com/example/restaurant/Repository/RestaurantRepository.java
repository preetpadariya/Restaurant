package com.example.restaurant.Repository;

import com.example.restaurant.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository  extends JpaRepository<Restaurant,Integer> {
    Optional<Restaurant> findByEmail(String Email);
    Optional<Restaurant> findByName(String Name);
    void deleteRestaurantById(Integer Id);
}