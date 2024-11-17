package com.example.springsecurityjwt.role.service;

import com.example.springsecurityjwt.role.dto.RoleRequestDTO;
import com.example.springsecurityjwt.role.dto.RoleResponseDTO;
import com.example.springsecurityjwt.role.mapper.RoleMapper;
import com.example.springsecurityjwt.role.model.Role;
import com.example.springsecurityjwt.role.repo.RoleRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepo roleRepo;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepo roleRepo,
                       RoleMapper roleMapper) {
        this.roleRepo = roleRepo;
        this.roleMapper = roleMapper;
    }

    public String save(RoleRequestDTO requestDTO) {
        Role role = roleMapper.toEntity(requestDTO);
        return roleRepo.save(role).getId();
    }

    public void update(String id, RoleRequestDTO requestDTO) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        Role role = roleOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roleMapper.toEntity(requestDTO, role);
        roleRepo.save(role);
    }

    public RoleResponseDTO findById(String id) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        Role role = roleOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        return roleMapper.toDTO(role);
    }

    public Page<RoleResponseDTO> findAll(Pageable pageable) {
        Page<Role> all = roleRepo.findAll(pageable);
        return roleMapper.toDTO(all);
    }

    public void delete(String id) {
        Optional<Role> roleOpt = roleRepo.findById(id);
        Role role = roleOpt.orElseThrow(() -> new RuntimeException(String.format("اطلاعاتی با شناسه %s یافت نشد.", id)));
        roleRepo.delete(role);
    }
}
