package com.example.springsecurityjwt.role.mapper;

import com.example.springsecurityjwt.base.BaseMapper;
import com.example.springsecurityjwt.role.dto.RoleRequestDTO;
import com.example.springsecurityjwt.role.dto.RoleResponseDTO;
import com.example.springsecurityjwt.role.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements BaseMapper<Role, RoleRequestDTO, RoleResponseDTO> {
    @Override
    public Role toEntity(RoleRequestDTO dto) {
        Role role = new Role();
        toEntity(dto, role);
        return role;
    }

    @Override
    public void toEntity(RoleRequestDTO dto, Role role) {
        role.setName(dto.getName());
        role.setCode(dto.getCode());
    }

    @Override
    public RoleResponseDTO toDTO(Role role) {
        RoleResponseDTO responseDTO = RoleResponseDTO.builder().build();
        toDTO(role, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(Role role, RoleResponseDTO dto) {
        dto.setName(role.getName());
        dto.setCode(role.getCode());
        baseField(dto, role);
    }
}
