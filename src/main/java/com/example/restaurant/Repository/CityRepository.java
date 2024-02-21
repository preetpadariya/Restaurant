package com.example.restaurant.Repository;

import com.example.restaurant.Model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Integer> {
    Optional<City> findByName(String Name);
    Page<City> findAll(Pageable pageable);

    void deleteCityById(Integer Id);
}