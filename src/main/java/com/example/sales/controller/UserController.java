package com.example.sales.controller;

import com.example.sales.dto.request.AddressRequestDTO;
import com.example.sales.dto.request.UserRequestDTO;
import com.example.sales.dto.request.UserUpdateRequestDTO;
import com.example.sales.dto.response.AddressResponseDTO;
import com.example.sales.dto.response.UserResponseDTO;
import com.example.sales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUser(@RequestHeader("Authorization") String authorization) throws Exception {
        return ResponseEntity.ok(userService.getUserByLogin(authorization));
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestHeader("Authorization") String authorization, @RequestBody UserUpdateRequestDTO request) throws Exception {
        userService.updateUser(authorization, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorization) {
        userService.deleteUser(authorization);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/address")
    public ResponseEntity<HttpStatus> addAddress(@RequestHeader("Authorization") String authorization, @RequestBody AddressRequestDTO request) throws Exception {
        userService.addAddress(authorization, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/address")
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses(@RequestHeader("Authorization") String authorization) throws Exception {
        return ResponseEntity.ok(userService.getAllAddressesByUser(authorization));
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@RequestHeader("Authorization") String authorization, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(userService.getAddressByUserAndId(authorization, id));
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@RequestHeader("Authorization") String authorization, @PathVariable Long id) throws Exception {
        userService.deleteAddress(authorization, id);
        return ResponseEntity.ok().build();
    }
}
