package com.example.restaurant.Model;


import com.example.restaurant.Security.ImageUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Base64;

@Getter
@Setter
@Entity
@ToString
@Table(name = "Complaints")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subject;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    private String complaintDate;

    private String replyDate;

    private String status;


    private String reply;
    @Lob
    private byte[] pdf;

    public Complaint(String subject, String description, Restaurant restaurant, String complaintDate, String status, byte[] pdf) {
        this.subject = subject;
        this.description = description;
        this.restaurant = restaurant;
        this.complaintDate = complaintDate;
        this.status = status;
        this.pdf = pdf;
    }

    public Complaint() {

    }
    public String getPhotoString() {
        return Base64.getEncoder().encodeToString(ImageUtils.decompressImage(pdf));
    }

}