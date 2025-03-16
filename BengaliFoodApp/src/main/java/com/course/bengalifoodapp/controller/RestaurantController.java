package com.course.bengalifoodapp.controller;

import com.course.bengalifoodapp.dto.RestaurantDto;
import com.course.bengalifoodapp.service.FileService;
import com.course.bengalifoodapp.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final RestaurantService restaurantService;
    private FileService fileService;

    public RestaurantController(RestaurantService restaurantService , FileService fileService) {
        this.restaurantService = restaurantService;
        this.fileService = fileService;
    }
    @PostMapping
    public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.addRestaurant(restaurantDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody RestaurantDto restaurantDto, @PathVariable String id) {
        return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<RestaurantDto>> getAllRestaurants(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                                 @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
                                                                 @RequestParam(value = "sortDir", required = false, defaultValue = "desc") String sortDir) {


        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(restaurantService.getAllRestaurants(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable String id) {
        return ResponseEntity.ok(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDto>> getRestaurantByName(@RequestParam String name) {
        return ResponseEntity.ok(restaurantService.getRestaurantByName(name));
    }

    @GetMapping("/status")
    public ResponseEntity<Page<RestaurantDto>> getRestaurantsByIsOpen(@RequestParam Boolean isOpen, Pageable pageable) {
        return ResponseEntity.ok(restaurantService.getRestaurantsByIsOpen(isOpen, pageable));
    }

    //Api to Handle Restaurant Banner
    @PostMapping("/upload-banner/{restaurantId}")
    public ResponseEntity<?> uploadFile(@PathVariable("restaurantId")@RequestParam("banner") MultipartFile banner) throws IOException {
        logger.info("Uploading banner file");
        logger.info(banner.getOriginalFilename());
        logger.info(banner.getContentType());

        String filePath ="uploads//" + banner.getOriginalFilename();
                fileService.uploadFile(banner,filePath);
        return ResponseEntity.ok("Banner file successfully uploaded");

    }
}
