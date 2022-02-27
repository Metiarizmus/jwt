package com.lessons.api;

import com.lessons.api.enums.RoleEnum;
import com.lessons.api.models.Role;
import com.lessons.api.models.User;
import com.lessons.api.repo.RoleRepo;
import com.lessons.api.repo.UserRepo;
import com.lessons.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;

@SpringBootApplication
@EnableMongoRepositories()
public class ApiApplication {




    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }


}
