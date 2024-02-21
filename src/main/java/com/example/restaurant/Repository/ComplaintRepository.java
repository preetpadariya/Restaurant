package com.example.restaurant.Repository;

import com.example.restaurant.Model.Complaint;
import com.example.restaurant.Model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
    List<Complaint> findAllByRestaurantId(Integer restaurantId);
}