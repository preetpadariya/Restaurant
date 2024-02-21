package com.example.restaurant.Repository;

import com.example.restaurant.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByid(Integer id);
    Optional<Product> findByName(String Name);
    void deleteProductById(Integer Id);
    @Query(value = "select * from products where restaurant_id = :id",nativeQuery = true)
    List<Product> findByRestaurantid(Integer id);

}