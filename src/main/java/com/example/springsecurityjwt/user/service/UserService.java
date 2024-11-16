package com.example.springsecurityjwt.user.service;

import com.example.springsecurityjwt.role.model.Role;
import com.example.springsecurityjwt.role.repo.RoleRepo;
import com.example.springsecurityjwt.security.jwt.JWTService;
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

    public UserService(UserRepo userRepo,
                       RoleRepo roleRepo,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JWTService jwtService) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String save(User user) {
        return userRepo.save(user).getId();
    }

    public User findById(String id) {
        Optional<User> userOptional = userRepo.findById(id);
        return userOptional.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    public void update(String id, User user) {
        Optional<User> userOpt = userRepo.findById(id);
        userOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userRepo.save(user);
    }

    public void delete(String id) {
        Optional<User> userOpt = userRepo.findById(id);
        User user = userOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        userRepo.delete(user);
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return user;
    }

    public String login(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
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
