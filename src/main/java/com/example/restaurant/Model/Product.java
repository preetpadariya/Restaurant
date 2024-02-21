package com.example.restaurant.Model;

import com.example.restaurant.Security.ImageUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Products")
public class Product {
    public Product(String productName, Integer productPrice, MultipartFile photo, String productDesc, Optional<Category> categoryModel, Optional<SubCategory> subcatModel, Optional<Restaurant> restaurant) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer price;

    @Basic(fetch = FetchType.LAZY)
    @Column(length = 16000000,nullable = true)
    private byte[] photo;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subcategory_id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;


    public Product(String name, Integer price, MultipartFile photo, String description, Category category, SubCategory subCategory, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        try {
            this.photo = ImageUtils.compressImage(photo.getBytes());
        } catch (IOException e) {
            System.out.println("NO IMAGE");
        }
        this.description = description;
        this.category = category;
        this.subCategory = subCategory;
        this.restaurant = restaurant;
    }

    public Product() {

    }

    public String getPhotoString() {
        return Base64.getEncoder().encodeToString(ImageUtils.decompressImage(photo));
    }
}
