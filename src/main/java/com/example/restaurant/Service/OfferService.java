package com.example.restaurant.Service;

import com.example.restaurant.Model.Offer;
import com.example.restaurant.Model.Product;
import com.example.restaurant.Repository.OfferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

//    public List<Offer> getAllOffersByRestaurantName(Integer id) {
//        return offerRepository.findAllByRestaurant(id);
//    }

    public Offer addOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public void deletOfferById(Integer id) {
        Offer off = getOfferById(id);
        off.setCategory(null);
        off.setRestaurant(null);
        off.setSubCategory(null);
        offerRepository.delete(off);
    }

    public Offer getOfferById(Integer id) {
        return offerRepository.findById(id).orElseThrow();
    }

    public void saveOffer(Offer u) {
        offerRepository.save(u);
    }

    public List<Offer> getAllOffersByRestaurant(Integer id) {
        return offerRepository.findAllByRestaurantId(id);
    }

}