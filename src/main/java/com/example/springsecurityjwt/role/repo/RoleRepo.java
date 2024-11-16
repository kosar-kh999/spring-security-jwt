package com.example.springsecurityjwt.role.repo;

import com.example.springsecurityjwt.role.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, String> {
}
