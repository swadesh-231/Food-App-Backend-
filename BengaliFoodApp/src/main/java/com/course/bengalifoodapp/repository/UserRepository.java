package com.course.bengalifoodapp.repository;

import com.course.bengalifoodapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    //custom query methods
    Optional<User> findByEmail(String email);
    List<User> findByUsername(String username);
    List<User> findByUsernameContaining(String keyword);
}
