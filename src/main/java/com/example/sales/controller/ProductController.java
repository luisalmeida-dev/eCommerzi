package com.example.sales.controller;

import com.example.sales.dto.request.ProductRequestDTO;
import com.example.sales.dto.request.ProductUpdateRequestDTO;
import com.example.sales.dto.response.ProductResponseDTO;
import com.example.sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByUser (@PathVariable Long userId) throws Exception {
        return ResponseEntity.ok(productService.getAllProductsByUser(userId));
    }
    @GetMapping("/user/{userId}/sku/{sku}") //TODO id do usuario vai ser pego de forma interna (jwt provavelmente)
    public ResponseEntity<ProductResponseDTO> getProductBySku(@PathVariable Long userId, @PathVariable String sku) throws Exception {
        return ResponseEntity.ok(productService.getProductByUserAndSku(userId, sku));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestBody ProductRequestDTO request) throws Exception {
        productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("{productId}/user/{userId}") //TODO id do usuario vai ser pego de forma interna (jwt provavelmente)
    public ResponseEntity<String> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequestDTO request, @PathVariable Long userId) throws Exception {
        productService.updateProduct(productId, request, userId);
        return ResponseEntity.ok("The product was successfully updated!");
    }
}
