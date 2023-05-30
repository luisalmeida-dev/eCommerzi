package com.example.sales.controller;

import com.example.sales.dto.request.DiscountRequestDTO;
import com.example.sales.dto.request.DiscountUpdateRequestDTO;
import com.example.sales.dto.response.DiscountResponseDTO;
import com.example.sales.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @PostMapping
    ResponseEntity<HttpStatus> create(@RequestBody DiscountRequestDTO request) throws Exception { //TODO Adicionar validacao do id de usuario && salvar o nome do status na base(mudar para string).
        discountService.createDiscount(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("user/{userId}")
        //TODO url vai mudar quando userId for passado na authorization
    ResponseEntity<List<DiscountResponseDTO>> getAllDiscountsByUser(@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(discountService.getAllDiscountsByUser(userId));
    }

    @PutMapping("/{discountId}")
    ResponseEntity<HttpStatus> updateDiscountById(@RequestBody DiscountUpdateRequestDTO request, @PathVariable Long discountId) {
        discountService.updateDiscountById(request, discountId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{discountId}")
    ResponseEntity<HttpStatus> deleteDiscountById(@PathVariable Long discountId) {
        discountService.deleteDiscountById(discountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
