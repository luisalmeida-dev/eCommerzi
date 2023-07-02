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

    @GetMapping("/{login}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String login) throws Exception {
        return ResponseEntity.ok(userService.getUserByLogin(login));
    }

    @PutMapping
    public ResponseEntity<HttpStatus> update(@RequestBody UserUpdateRequestDTO request) throws Exception {
        userService.updateUser(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{login}") //TODO alterar para deletar todas as informacoes que fazem referencia ao usuario(tabelas que utilizam o userId)
    public ResponseEntity<Void> delete(@PathVariable String login) throws Exception { //TODO colocar validacao de usuario com spring security, para validar se o usuario que quer deletar a conta eh o mesmo que ta fazendo o request
        userService.deleteUser(login);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/address")
    public ResponseEntity<HttpStatus> addAddress(@RequestBody AddressRequestDTO request) throws Exception {
        userService.addAddress(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/address")
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(userService.getAllAddressesByUser(userId));
    }

    @DeleteMapping("/{userId}/address/{zipcode}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable Long userId, @PathVariable String zipcode) throws Exception {
        userService.deleteAddress(userId, zipcode);
        return ResponseEntity.ok().build();
    }
}
