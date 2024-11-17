package com.example.springsecurityjwt.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class BaseEntity {

    @Id
    private String id;
}
