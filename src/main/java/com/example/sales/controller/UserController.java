package com.example.sales.controller;

import com.example.sales.dto.request.UserRequsetDTO;
import com.example.sales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody UserRequsetDTO request) throws Exception {
        userService.createUser(request);
        return ResponseEntity.ok("O usuario foi criado com sucesso!")
                ;
    }

}
