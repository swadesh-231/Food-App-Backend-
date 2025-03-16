package com.course.bengalifoodapp.service;

import com.course.bengalifoodapp.dto.RestaurantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface RestaurantService {
    RestaurantDto addRestaurant(RestaurantDto restaurantDto);
    RestaurantDto updateRestaurant(RestaurantDto restaurantDto,String id);
    void deleteRestaurant(String id);
    Page<RestaurantDto> getAllRestaurants(Pageable pageable);
    RestaurantDto getRestaurantById(String id);
    List<RestaurantDto> getRestaurantByName(String restaurantName);
    Page<RestaurantDto> getRestaurantsByIsOpen(Boolean isOpen, Pageable pageable);

}
