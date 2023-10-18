package com.example.sales.service;

import com.example.sales.auth.service.TokenService;
import com.example.sales.dto.request.OrderRequestDTO;
import com.example.sales.dto.response.OrderResponseDTO;
import com.example.sales.mapper.OrderMapper;
import com.example.sales.model.OrderEntity;
import com.example.sales.model.ProductEntity;
import com.example.sales.repository.OrderRepository;
import com.example.sales.repository.ProductRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CarrierService carrierService;
    @Autowired
    private TokenService tokenService;


    public void createOrder(String authorization, OrderRequestDTO request) throws Exception { //TODO create validation for stores, carriers, products, payments
        ProductEntity product = productRepository.findById(request.getProductId()).orElseThrow(() -> new Exception("Product Not Found!"));
        if (product.getQuantity() >= request.getQuantity()) {
            product.setQuantity(product.getQuantity() - request.getQuantity());

            BigDecimal shippingPrice = carrierService.shippingPrice(product.getPrice(), request.getCarrierId());

            OrderEntity order = new OrderEntity();
            order.setShippingPrice(shippingPrice);
            order.setTotal(product.getPrice().add(order.getShippingPrice()));
            order.setBuyerId(tokenService.decodeToken(authorization).getClaim("userid").asLong());

            orderMapper.orderRequestToEntity(request);

            productRepository.save(product);
            orderRepository.save(order);
        } else {
            throw new Exception("There's only " + product.getQuantity() + "units available");
        }
    }

    public List<OrderResponseDTO> getOrdersByUser(String authorization) throws Exception {
        List<OrderEntity> orderList = orderRepository.getAllByBuyerId(tokenService.decodeToken(authorization).getClaim("userid").asLong());
        if (!orderList.isEmpty()) {
            return orderList.stream()
                    .map(orderMapper::orderEntityToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new Exception("You didn't ordered yet!");
        }
    }
}
