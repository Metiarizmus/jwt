package com.lessons.api.services;

import com.lessons.api.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    User getUserByEmail(String email);
    List<User> getAllUser();
    Optional<User> findById(String id);
}
