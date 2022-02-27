package com.lessons.api.repo;

import com.lessons.api.enums.RoleEnum;
import com.lessons.api.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RoleRepo extends MongoRepository<Role, String> {
    Optional<Role> findByRoleName(RoleEnum name);
}
