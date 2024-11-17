package com.example.springsecurityjwt.user.dto;

import com.example.springsecurityjwt.base.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO extends RequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
}
