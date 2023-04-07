package com.example.sales.controller;

import com.example.sales.dto.request.CardRequestDTO;
import com.example.sales.dto.request.CardUpdateRequestDTO;
import com.example.sales.dto.response.CardResponseDTO;
import com.example.sales.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    //TODO validate if getCard will stay here or got userController && users will be able to register more than one card, so it must return a list of cards.
    @GetMapping("/{userId}")
    public ResponseEntity<CardResponseDTO> getCard(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(cardService.getCardById(userId));
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
