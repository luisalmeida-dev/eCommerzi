package com.example.sales.controller;

import com.example.sales.dto.request.OrderRequestDTO;
import com.example.sales.dto.response.OrderResponseDTO;
import com.example.sales.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    ResponseEntity<HttpStatus> createOrder(@RequestBody OrderRequestDTO request) throws Exception {
        orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/userId/{id}")
    ResponseEntity<List<OrderResponseDTO>> getOrdersByUser(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByUser(id));
    }
}
