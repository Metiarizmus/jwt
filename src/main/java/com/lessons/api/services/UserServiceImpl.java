package com.lessons.api.services;

import com.lessons.api.models.User;
import com.lessons.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        log.info("saving user {} to the db", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    @Override
    public User getUserByEmail(String email) {
        log.info("fetching user with email {}", email);
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        log.info("fetching all user");
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepo.findById(id);
    }

}
