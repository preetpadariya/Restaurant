package com.example.restaurant.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Restaurant")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    @Column(unique = true)
    private String email;

    private String address;

    private String contactnumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    public Restaurant(String name, String password, String email, String address, String contactnumber, City city, Area area) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.contactnumber = contactnumber;
        this.city = city;
        this.area = area;
    }

    public Restaurant() {

    }
}
