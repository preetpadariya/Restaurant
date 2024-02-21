package com.example.restaurant.Repository;

import com.example.restaurant.Model.Area;
import com.example.restaurant.Model.Category;
import com.example.restaurant.Model.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubCatRepository extends JpaRepository<SubCategory,Integer> {
    Optional<SubCategory> findByName(String Name);
    Page<SubCategory> findAll(Pageable pageable);

    void deleteSubCategoryById(Integer Id);
    @Query(value = "select * from sub_category where category_id = :id",nativeQuery = true)
    List<SubCategory> findByCategoryId(@Param("id") int id);
}