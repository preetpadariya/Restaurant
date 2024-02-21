package com.example.restaurant.Repository;

import com.example.restaurant.Model.Area;
import com.example.restaurant.Model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area,Integer> {
    Optional<Area> findByName(String Name);
    Page<Area> findAll(Pageable pageable);

    void deleteAreaById(Integer Id);

    @Query(value = "select * from areas where city_id = :id",nativeQuery = true)
    List<Area> findByCityId(@Param("id") int id);
}