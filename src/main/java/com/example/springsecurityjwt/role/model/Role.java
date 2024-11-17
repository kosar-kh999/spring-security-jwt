package com.example.springsecurityjwt.role.model;

import com.example.springsecurityjwt.base.BaseEntity;
import com.example.springsecurityjwt.user.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Setter
@Getter
@Document(collection = "role")
public class Role extends BaseEntity {

    private String name;
    private String code;

    private Set<User> users;
}
