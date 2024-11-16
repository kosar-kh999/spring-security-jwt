package com.example.springsecurityjwt.role.service;

import com.example.springsecurityjwt.role.model.Role;
import com.example.springsecurityjwt.role.repo.RoleRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public String save(Role role) {
        return roleRepo.save(role).getId();
    }

    public void update(String id, Role role) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        roleOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roleRepo.save(role);
    }

    public Role findById(String id) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        return roleOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
    }

    public Page<Role> findAll(Pageable pageable) {
        return roleRepo.findAll(pageable);
    }

    public void delete(String id) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        Role role = roleOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roleRepo.delete(role);
    }
}
