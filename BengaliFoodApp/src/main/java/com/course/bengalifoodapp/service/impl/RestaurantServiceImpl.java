package com.course.bengalifoodapp.service.impl;

import com.course.bengalifoodapp.dto.RestaurantDto;
import com.course.bengalifoodapp.entity.Restaurant;
import com.course.bengalifoodapp.repository.RestaurantRepository;
import com.course.bengalifoodapp.service.RestaurantService;
import com.course.bengalifoodapp.utils.HelperMethod;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
        // Generate and set a random ID for the restaurant DTO
        restaurantDto.setId(HelperMethod.getRandomId());
        // Convert RestaurantDto to Restaurant entity using ModelMapper
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        // Save the restaurant entity to the database via repository
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        // Convert saved Restaurant entity back to RestaurantDto and return it
        return modelMapper.map(savedRestaurant, RestaurantDto.class);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, String id) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));

        // Map updated fields from DTO to entity
        modelMapper.map(restaurantDto, existingRestaurant);

        // Ensure ID remains unchanged
        existingRestaurant.setId(id);

        // Save updated entity
        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);

        // Map back to DTO and return
        return modelMapper.map(updatedRestaurant, RestaurantDto.class);
    }

    @Override
    public void deleteRestaurant(String id) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        restaurantRepository.delete(existingRestaurant);
    }

    @Override
    public Page<RestaurantDto> getAllRestaurants(Pageable pageable) {
        Page<Restaurant> PageRestaurants = restaurantRepository.findAll(pageable);
        return PageRestaurants.map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class));
    }

    @Override
    public RestaurantDto getRestaurantById(String id) {
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
        return modelMapper.map(existingRestaurant, RestaurantDto.class);
    }

    @Override
    public List<RestaurantDto> getRestaurantByName(String restaurantName) {
        return restaurantRepository.findByNameContainingIgnoreCase(restaurantName)
                .stream()
                .map(restaurant -> modelMapper
                        .map(restaurant, RestaurantDto.class))
                .collect(Collectors.toList());
    }
    @Override
    public Page<RestaurantDto> getRestaurantsByIsOpen(Boolean isOpen, Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findByIsOpen(true, pageable);
        return restaurants.map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class));
    }

}
