package com.example.sales.controller;

import com.example.sales.dto.request.CardRequestDTO;
import com.example.sales.dto.request.CardUpdateRequestDTO;
import com.example.sales.dto.response.CardResponseDTO;
import com.example.sales.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<CardResponseDTO>> getAllCardsByUser(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(cardService.getAllCardsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDTO> getCard(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody CardRequestDTO request) throws Exception {
        cardService.createCard(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> update(@PathVariable Long userId, @RequestBody CardUpdateRequestDTO request) throws Exception {
        cardService.updateCard(userId, request);
        return ResponseEntity.ok().body("The user was successfully updated!");
    }

    @DeleteMapping("/{cardId}/user/{userId}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long cardId, @PathVariable Long userId) throws Exception {
        cardService.deleteCard(cardId, userId);
        return ResponseEntity.ok().build();
    }
}
