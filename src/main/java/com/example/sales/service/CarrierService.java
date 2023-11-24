package com.example.sales.service;

import com.example.sales.dto.response.AddressResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class CarrierService {
    @Autowired
    private UserService userService;

    public BigDecimal shippingPrice(BigDecimal productPrice, Integer carrierId) {
        if (carrierId % 2 == 0) {
            productPrice = productPrice.multiply(new BigDecimal("0.065"));
        } else {
            productPrice = productPrice.multiply(new BigDecimal("0.155"));
        }
        return productPrice;
    }

    public String generateTrackingNumber(String authorization) throws Exception {
        List<AddressResponseDTO> addressList = userService.getAllAddressesByUser(authorization);
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        long number = (rng.nextLong() % 900000000L) + 100000000L;
        return addressList.get(0).getState().getUf() + number + "BR";
    }
}