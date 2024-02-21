package com.example.restaurant.Service;

import com.example.restaurant.Model.Product;
import com.example.restaurant.Model.Restaurant;
import com.example.restaurant.Repository.ProductRepository;
import com.example.restaurant.Repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductService {
    private final ProductRepository r;

    @Autowired
    public ProductService(ProductRepository u){
        r = u;
    }
    public Optional<Product> getproductbyid(Integer id){return r.findById(id);}
    public void deleteproductbyid(Integer i){
        Product p = r.getById(i);
        p.setRestaurant(null);
        p.setSubCategory(null);
        p.setCategory(null);
        r.delete(p);
        //r.deleteProductById(i);
    }
    public List<Product> getallproduct(){return r.findAll();}
    public void saveProduct(Product u) {
        r.save(u);
    }

    public List<Product> getallproductbyRestaurantId(Integer id) {
        return r.findByRestaurantid(id);
    }
}