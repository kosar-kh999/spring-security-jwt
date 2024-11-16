package com.example.springsecurityjwt.user.repo;

import com.example.springsecurityjwt.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByUsername(String username);
}
