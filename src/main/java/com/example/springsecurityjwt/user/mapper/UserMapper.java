package com.example.springsecurityjwt.user.mapper;

import com.example.springsecurityjwt.base.BaseMapper;
import com.example.springsecurityjwt.user.dto.UserRequestDTO;
import com.example.springsecurityjwt.user.dto.UserResponseDTO;
import com.example.springsecurityjwt.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements BaseMapper<User, UserRequestDTO, UserResponseDTO> {

    @Override
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        toEntity(dto, user);
        return user;
    }

    @Override
    public void toEntity(UserRequestDTO dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
    }

    @Override
    public UserResponseDTO toDTO(User user) {
        UserResponseDTO responseDTO = UserResponseDTO.builder().build();
        toDTO(user, responseDTO);
        return responseDTO;
    }

    @Override
    public void toDTO(User user, UserResponseDTO dto) {
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        baseField(dto, user);
    }
}
