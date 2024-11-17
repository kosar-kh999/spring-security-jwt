package com.example.springsecurityjwt.user.model;

import com.example.springsecurityjwt.base.BaseEntity;
import com.example.springsecurityjwt.role.model.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Document(collection = "user")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private Set<Role> roles = new HashSet<>();
}
