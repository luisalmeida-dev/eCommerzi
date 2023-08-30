package com.example.sales.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarrierService {
    public BigDecimal shippingPrice(BigDecimal productPrice, Long carrierId) {
        if (carrierId % 2 == 0) {
            productPrice = productPrice.multiply(new BigDecimal("0.065"));
        } else {
            productPrice = productPrice.multiply(new BigDecimal("0.155"));
        }
        return productPrice;
    }
}
