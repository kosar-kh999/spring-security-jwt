package com.example.springsecurityjwt.role.controller;

import com.example.springsecurityjwt.role.dto.RoleRequestDTO;
import com.example.springsecurityjwt.role.dto.RoleResponseDTO;
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
    public ResponseEntity<String> add(@RequestBody RoleRequestDTO requestDTO) {
        String savedId = roleService.save(requestDTO);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/role/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody RoleRequestDTO requestDTO) {
        roleService.update(id, requestDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/role/{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable String id) {
        RoleResponseDTO role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @GetMapping(value = "/role")
    public ResponseEntity<Page<RoleResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        Page<RoleResponseDTO> page = roleService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
