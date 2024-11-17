package com.example.springsecurityjwt.user.dto;

import com.example.springsecurityjwt.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class UserResponseDTO extends ResponseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
