package com.example.springsecurityjwt.user.controller;

import com.example.springsecurityjwt.user.model.User;
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
    public ResponseEntity<String> add(@RequestBody User user) {
        String savedId = userService.save(user);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        User responseDTO = userService.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<Page<User>> getAll(@PageableDefault Pageable pageable) {
        Page<User> page = userService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);

    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping(value = "/user/role")
    public ResponseEntity<Void> assignRoleForUser(@RequestBody UserRoleRecord requestDTO) {
        userService.assignRoleForUser(requestDTO);
        return ResponseEntity.noContent().build();
    }
}
