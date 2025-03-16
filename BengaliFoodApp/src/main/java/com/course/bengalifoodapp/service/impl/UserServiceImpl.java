package com.course.bengalifoodapp.service.impl;

import com.course.bengalifoodapp.dto.UserDto;

import com.course.bengalifoodapp.entity.User;
import com.course.bengalifoodapp.exception.ResourceNotFoundException;
import com.course.bengalifoodapp.repository.UserRepository;
import com.course.bengalifoodapp.service.UserService;
import com.course.bengalifoodapp.utils.HelperMethod;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        userDto.setId(HelperMethod.getRandomId());
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        modelMapper.map(userDto, existingUser);
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public List<UserDto> getUserByName(String userName) {
        List<User> users = userRepository.findByUsername(userName);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return modelMapper.map(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email)),
                UserDto.class
        );
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> searchUserName(String keyword) {
        List<User> users = userRepository.findByUsernameContaining(keyword);
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

}
