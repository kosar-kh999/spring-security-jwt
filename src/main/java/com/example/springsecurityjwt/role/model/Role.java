package com.example.springsecurityjwt.role.model;

import com.example.springsecurityjwt.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Document(collection = "role")
public class Role {

    @Id
    private String id;
    private String name;
    private String code;

    private Set<User> users = new HashSet<>();
}
