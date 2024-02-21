package com.example.restaurant.Repository;

import com.example.restaurant.Model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    Optional<Offer> findByOfferName(String Name);
    Page<Offer> findAll(Pageable pageable);
    void deleteOfferById(Integer Id);

    List<Offer> findAllByRestaurantId(Integer restaurantId);

}