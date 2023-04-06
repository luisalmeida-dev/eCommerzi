package com.example.sales.controller;

import com.example.sales.dto.response.CardResponseDTO;
import com.example.sales.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDTO> getCard (@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(cardService.getCardById(id));
    }
}
