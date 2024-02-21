package com.example.restaurant.Repository;

import com.example.restaurant.Model.Area;
import com.example.restaurant.Model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findByName(String Name);
    Page<Category> findAll(Pageable pageable);
    void deleteCategoryById(Integer Id);
}