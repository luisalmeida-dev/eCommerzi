package com.example.sales.controller;

import com.example.sales.dto.request.CreateRoleRequestDTO;
import com.example.sales.model.RoleEntity;
import com.example.sales.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping()
    public ResponseEntity<?> createRole(@RequestBody CreateRoleRequestDTO role) throws Exception {
        roleService.createRole(role);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRole(@PathVariable Long id) throws Exception {
        return roleService.getRole(id);
    }
}