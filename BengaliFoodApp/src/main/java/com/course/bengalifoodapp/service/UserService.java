package com.course.bengalifoodapp.service;

import com.course.bengalifoodapp.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface UserService {
   UserDto saveUser(UserDto userDto);
   Page<UserDto> getAll(Pageable pageable);
   UserDto updateUser(UserDto userDto, String userId);
   List<UserDto> getUserByName(String userName);
   UserDto getUserByEmail(String email);
   UserDto getUserById(String userId);
   void deleteUser(String userId);
   List<UserDto> searchUserName(String keyword);
}
