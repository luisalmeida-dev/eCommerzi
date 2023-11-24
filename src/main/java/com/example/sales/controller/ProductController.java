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

    @PostMapping
    public ResponseEntity<HttpStatus> createProduct(@RequestHeader("Authorization") String authorization, @RequestBody ProductRequestDTO request) throws Exception {
        productService.createProduct(authorization, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByUser(@RequestHeader("Authorization") String authorization) throws Exception {
        return ResponseEntity.ok(productService.getAllProductsByUser(authorization));
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductResponseDTO> getProductBySku(@RequestHeader("Authorization") String authorization, @PathVariable String sku) throws Exception {
        return ResponseEntity.ok(productService.getProductByUserAndSku(authorization, sku));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@RequestHeader("Authorization") String authorization, @PathVariable Integer id, @RequestBody ProductUpdateRequestDTO request) throws Exception {
        productService.updateProduct(authorization, id, request);
        return ResponseEntity.ok("The product was successfully updated!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProductById(@RequestHeader("Authorization") String authorization, @PathVariable Integer id) {
        productService.deleteProductById(authorization, id);
        return ResponseEntity.ok().build();
    }
}