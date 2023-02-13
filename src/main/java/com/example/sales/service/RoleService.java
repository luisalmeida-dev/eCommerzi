package com.example.sales.service;

import com.example.sales.dto.request.CreateRoleRequestDTO;
import com.example.sales.model.RoleEntity;
import com.example.sales.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void createRole(CreateRoleRequestDTO role) throws Exception {
        if (roleRepository.findByRole(role.getRole()) == null) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRole(role.getRole());
            roleRepository.save(roleEntity);
        } else {
            throw new Exception("This role already exist");
        }
    }

    public ResponseEntity<RoleEntity> getRole(Long id) throws Exception {
        RoleEntity role = roleRepository.findById(id).orElseThrow(() -> new Exception("Role doesn't exist"));
        return ResponseEntity.ok(role);
    }
}
