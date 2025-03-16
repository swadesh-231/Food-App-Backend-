package com.course.bengalifoodapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bengali_food_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isAvailable = true;
    private LocalDate createdDate;

    // This annotation is used to establish a one-to-many relationship between two entities.
// - `mappedBy = "user"`: Indicates that the relationship is mapped by the "user" field in the child entity.
// - `cascade = CascadeType.ALL`: Ensures that all operations (persist, merge, remove, refresh, detach) are cascaded to the child entities.
// - `fetch = FetchType.EAGER`: Fetches the associated child entities immediately when the parent entity is loaded.
// - `orphanRemoval = true`: Automatically removes child entities when they are removed from the parent entity’s collection.
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Restaurant> restaurants = new ArrayList<>();

}
