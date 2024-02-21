package com.example.restaurant.Model;
import com.example.restaurant.Security.ImageUtils;
import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String offerName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subCategory_id", nullable = false)
    private SubCategory subCategory;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private Integer discount;

    private String description;

    private String startDate;

    private String endDate;

    public Offer(String offerName, Integer offerDiscount, String startTime,String endTime, String offerDesc, Category categoryModel, SubCategory subcatModel, Restaurant restaurant) {
        this.offerName = offerName;
        this.discount = offerDiscount;
        this.description = offerDesc;
        this.endDate = endTime;
        this.startDate = startTime;
        this.category = categoryModel;
        this.subCategory = subcatModel;
        this.restaurant = restaurant;
    }
}
