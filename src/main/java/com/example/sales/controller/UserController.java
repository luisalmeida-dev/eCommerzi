package com.example.sales.controller;

import com.example.sales.dto.request.UserRequsetDTO;
import com.example.sales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody UserRequsetDTO request) throws Exception {
        userService.createUser(request);
        return ResponseEntity.ok("The user was successfully created!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        return ResponseEntity.ok("This is user: " + id);
    }
}
