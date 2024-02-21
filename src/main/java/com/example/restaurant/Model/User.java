package com.example.restaurant.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    @Column(unique = true)
    private String email;

    private String role;
    public User() {
    }
    public User(String name, String email, String password, String role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}