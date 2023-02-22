package com.example.sales.controller;

import com.example.sales.dto.request.UserUpdateRequestDTO;
import com.example.sales.dto.request.UserRequsetDTO;
import com.example.sales.dto.response.UserResponseDTO;
import com.example.sales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody UserRequsetDTO request) throws Exception {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String cpf) throws Exception {
        return ResponseEntity.ok(userService.getUserByCpf(cpf));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody UserUpdateRequestDTO requset) throws Exception {
        userService.updateUser(requset);
        return ResponseEntity.ok("The user was successfully updated!");
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> delete(@PathVariable String cpf) throws Exception {
        userService.deleteUser(cpf);
        return ResponseEntity.ok().build();
    }
}
