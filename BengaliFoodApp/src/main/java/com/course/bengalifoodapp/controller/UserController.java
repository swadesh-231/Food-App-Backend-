package com.course.bengalifoodapp.controller;

import com.course.bengalifoodapp.dto.UserDto;
import com.course.bengalifoodapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create user

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    //get all users

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                     @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                     @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
                                                     @RequestParam(value = "sortDir", required = false, defaultValue = "desc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(userService.getAll(pageable));
    }

    //get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
    // Get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    // Get users by name
    @GetMapping("/name/{userName}")
    public ResponseEntity<List<UserDto>> getUserByName(@PathVariable("userName") String userName) {
        return ResponseEntity.ok(userService.getUserByName(userName));
    }

    // Search users by keyword
    @GetMapping("/search")
    public ResponseEntity<List<UserDto>> searchUserByName(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(userService.searchUserName(keyword));
    }

    // Update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.updateUser(userDto, userId));
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
