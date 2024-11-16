package com.example.springsecurityjwt.role.controller;

import com.example.springsecurityjwt.role.model.Role;
import com.example.springsecurityjwt.role.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(value = "/role")
    public ResponseEntity<String> add(@RequestBody Role role) {
        String savedId = roleService.save(role);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/role/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody Role role) {
        roleService.update(id, role);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/role/{id}")
    public ResponseEntity<Role> getById(@PathVariable String id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @GetMapping(value = "/role")
    public ResponseEntity<Page<Role>> getAll(@PageableDefault Pageable pageable) {
        Page<Role> page = roleService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
