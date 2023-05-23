package com.example.sales.controller;

import com.example.sales.dto.request.UserRequestDTO;
import com.example.sales.dto.request.UserUpdateRequestDTO;
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
    public ResponseEntity<HttpStatus> create(@RequestBody UserRequestDTO request) throws Exception {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String login) throws Exception {
        return ResponseEntity.ok(userService.getUserByLogin(login));
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody UserUpdateRequestDTO request) throws Exception {
        userService.updateUser(request);
        return ResponseEntity.ok("The user was successfully updated!"); //TODO fazer retornar void
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> delete(@PathVariable String login) throws Exception {
        userService.deleteUser(login);
        return ResponseEntity.ok().build();
    }
}
