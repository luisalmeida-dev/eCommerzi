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

    @GetMapping("/all")
    public ResponseEntity<List<CardResponseDTO>> getAllCardsByUser(@RequestHeader("Authorization") String authorization) throws Exception {
        return ResponseEntity.ok(cardService.getAllCardsByUser(authorization));
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponseDTO> getCard(@PathVariable Long cardId) throws Exception {
        return ResponseEntity.ok(cardService.getCardById(cardId));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestHeader("Authorization") String authorization, @RequestBody CardRequestDTO request) throws Exception {
        cardService.createCard(authorization, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestHeader("Authorization") String authorization, @RequestBody CardUpdateRequestDTO request) throws Exception {
        cardService.updateCard(authorization, request);
        return ResponseEntity.ok().body("The user was successfully updated!");
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<HttpStatus> delete(@RequestHeader("Authorization") String authorization, @PathVariable Long cardId) throws Exception {
        cardService.deleteCard(authorization, cardId);
        return ResponseEntity.ok().build();
    }
}
