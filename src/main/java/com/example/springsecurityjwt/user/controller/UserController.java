package com.example.springsecurityjwt.user.controller;

import com.example.springsecurityjwt.user.dto.UserRequestDTO;
import com.example.springsecurityjwt.user.dto.UserResponseDTO;
import com.example.springsecurityjwt.user.record.UserRoleRecord;
import com.example.springsecurityjwt.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> add(@RequestBody UserRequestDTO requestDTO) {
        String savedId = userService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserRequestDTO requestDTO) {
        userService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable String id) {
        UserResponseDTO responseDTO = userService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<Page<UserResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<UserResponseDTO> page = userService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public UserResponseDTO register(@RequestBody UserRequestDTO requestDTO) {
        return userService.register(requestDTO);

    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDTO requestDTO) {
        return userService.login(requestDTO);
    }

    @PostMapping(value = "/user/role")
    public ResponseEntity<Void> assignRoleForUser(@RequestBody UserRoleRecord requestDTO) {
        userService.assignRoleForUser(requestDTO);
        return ResponseEntity.noContent().build();
    }
}
