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
    ResponseEntity<HttpStatus> create(@RequestHeader("Authorization") String authorization, @RequestBody DiscountRequestDTO request) throws Exception {
        discountService.createDiscount(authorization, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    ResponseEntity<List<DiscountResponseDTO>> getAllDiscountsByUser(@RequestHeader("Authorzation") String authorization) throws Exception {
        return ResponseEntity.ok(discountService.getAllDiscountsByUser(authorization));
    }

    @PutMapping("/{discountId}")
    ResponseEntity<HttpStatus> updateDiscountById(@RequestHeader("Authorization") String authorization, @RequestBody DiscountUpdateRequestDTO request, @PathVariable Long discountId) {
        discountService.updateDiscountById(authorization, request, discountId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{discountId}")
    ResponseEntity<HttpStatus> deleteDiscountById(@RequestHeader("Auhtorization") String authorization, @PathVariable Long discountId) {
        discountService.deleteDiscountById(authorization, discountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
