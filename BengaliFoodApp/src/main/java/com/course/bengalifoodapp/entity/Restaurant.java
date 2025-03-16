package com.course.bengalifoodapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalTime;

@Entity
@Table(name = "bengali_restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    private String id;
    private String name;
    @Lob
    private String description;
    private String address;
    private String phone;
    private LocalTime openTine;
    private LocalTime closeTine;
    private Boolean isOpen = true;
    private String banner;
    @ManyToOne
    private User user;
}
