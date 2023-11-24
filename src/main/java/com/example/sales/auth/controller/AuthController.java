package com.example.sales.auth.controller;

import com.example.sales.auth.service.AuthService;
import com.example.sales.dto.request.EmailRequestDTO;
import com.example.sales.dto.request.LoginRequestDTO;
import com.example.sales.dto.request.UserRequestDTO;
import com.example.sales.dto.response.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        return ResponseEntity.ok(authService.login(data));
    }

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid UserRequestDTO userRequest) throws Exception {
        authService.register(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/activation-code")
    public ResponseEntity<Integer> sendActivationCode(@RequestBody EmailRequestDTO emailRequest) throws AccountNotFoundException {
        //TODO implementar o envio de email
        return ResponseEntity.ok(authService.generateActivationCode(emailRequest));
    }

    @PostMapping("/activate-account/{code}")
    public ResponseEntity<HttpStatus> activateAccount(@RequestBody EmailRequestDTO emailRequest, @PathVariable Integer code) throws Exception {
        authService.validateCode(emailRequest, code);
        return ResponseEntity.ok().build();
    }
}