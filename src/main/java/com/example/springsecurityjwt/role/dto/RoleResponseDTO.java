package com.example.springsecurityjwt.role.dto;

import com.example.springsecurityjwt.base.ResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class RoleResponseDTO extends ResponseDTO {
    private String name;
    private String code;
}
