package com.course.bengalifoodapp.repository;

import com.course.bengalifoodapp.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    List<Restaurant> findByNameContainingIgnoreCase(String keyword);
    Page<Restaurant> findByIsOpen(Boolean flag, Pageable pageable);
}
