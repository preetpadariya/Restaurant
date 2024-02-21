package com.example.restaurant.Service;


import com.example.restaurant.Model.Restaurant;
import com.example.restaurant.Model.User;
import com.example.restaurant.Repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository u){
        restaurantRepository = u;
    }
    public void saverestaurant(Restaurant u){restaurantRepository.save(u);}
    public Optional<Restaurant> getuserbyid(Integer id){return restaurantRepository.findById(id);}
    public void deleteuserbyid(Integer i){restaurantRepository.deleteRestaurantById(i);}
    public Optional<Restaurant> getuserbyemail(String username) {
        return restaurantRepository.findByEmail(username);
    }
    public Optional<Restaurant> getuserbyrole(String role) {
        return restaurantRepository.findByName(role);
    }
    public List<Restaurant> getallrestaurant(){return restaurantRepository.findAll();}
}