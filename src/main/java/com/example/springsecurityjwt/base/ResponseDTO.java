package com.example.springsecurityjwt.base;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class ResponseDTO {
    private String id;
}
