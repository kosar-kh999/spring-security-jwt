package com.example.springsecurityjwt.user.service;

import com.example.springsecurityjwt.role.model.Role;
import com.example.springsecurityjwt.role.repo.RoleRepo;
import com.example.springsecurityjwt.security.jwt.JWTService;
import com.example.springsecurityjwt.user.dto.UserRequestDTO;
import com.example.springsecurityjwt.user.dto.UserResponseDTO;
import com.example.springsecurityjwt.user.mapper.UserMapper;
import com.example.springsecurityjwt.user.model.User;
import com.example.springsecurityjwt.user.record.UserRoleRecord;
import com.example.springsecurityjwt.user.repo.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserMapper userMapper;

    public UserService(UserRepo userRepo,
                       RoleRepo roleRepo,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JWTService jwtService,
                       UserMapper userMapper) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    public String save(UserRequestDTO requestDTO) {
        User user = userRepo.findByUsername(requestDTO.getUsername());
        if (user == null)
            throw new RuntimeException(String.format("کاربر  %s قبلا ثبت نام کرده است.", requestDTO.getUsername()));
        User newUser = userMapper.toEntity(requestDTO);
        return userRepo.save(newUser).getId();
    }

    public UserResponseDTO findById(String id) {
        Optional<User> userOptional = userRepo.findById(id);
        User user = userOptional.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return userMapper.toDTO(user);
    }

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> users = userRepo.findAll(pageable);
        return userMapper.toDTO(users);
    }

    public void update(String id, UserRequestDTO requestDTO) {
        Optional<User> userOpt = userRepo.findById(id);
        User user = userOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userMapper.toEntity(requestDTO, user);
        userRepo.save(user);
    }

    public void delete(String id) {
        Optional<User> userOpt = userRepo.findById(id);
        User user = userOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userRepo.delete(user);
    }

    public UserResponseDTO register(UserRequestDTO requestDTO) {
        requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        User user = userMapper.toEntity(requestDTO);
        userRepo.save(user);
        return userMapper.toDTO(user);
    }

    public String login(UserRequestDTO requestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(requestDTO.getUsername());
        throw new RuntimeException("اطلاعات وارد شده صحیح نمیباشد.");
    }

    public void assignRoleForUser(UserRoleRecord userRecord) {
        Optional<User> userOpt = userRepo.findById(userRecord.userId());
        User user = userOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", userRecord.userId())));

        for (String roleId : userRecord.roleIds()) {
            Role role = roleRepo.findById(roleId).orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", roleId)));
            user.getRoles().add(role);
        }
        userRepo.save(user);
    }
}
