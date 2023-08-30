package com.example.sales.service;

import com.example.sales.dto.request.OrderRequestDTO;
import com.example.sales.dto.response.OrderResponseDTO;
import com.example.sales.mapper.OrderMapper;
import com.example.sales.model.OrderEntity;
import com.example.sales.model.ProductEntity;
import com.example.sales.model.UserEntity;
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
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CarrierService carrierService;


    public void createOrder(OrderRequestDTO request) throws Exception { //TODO create validation for stores, carriers, products, payments
        validateUser(request.getBuyerId());
        ProductEntity product = productRepository.findById(request.getProductId()).orElseThrow(() -> new Exception("Product Not Found!"));
        if (product.getQuantity() >= request.getQuantity()) {
            product.setQuantity(product.getQuantity() - request.getQuantity());

            BigDecimal shippingPrice = carrierService.shippingPrice(product.getPrice(), request.getCarrierId());

            OrderEntity order = new OrderEntity();
            order.setShippingPrice(shippingPrice);
            order.setTotal(product.getPrice().add(order.getShippingPrice()));

            orderRepository.save(order);
            productRepository.save(product);
        } else {
            throw new Exception("There's only " + product.getQuantity() + "units available");
        }
    }

    public List<OrderResponseDTO> getOrdersByUser(Long userId) throws Exception {
        validateUser(userId);
        List<OrderEntity> orderList = orderRepository.getAllByBuyerId(userId);
        if (!orderList.isEmpty()) {
            return orderList.stream()
                    .map(orderMapper::orderEntityToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new Exception("You didn't ordered yet!");
        }
    }

    private void validateUser(Long buyerId) throws Exception {
        UserEntity buyer = userRepository.findById(buyerId).orElseThrow(() -> new Exception("This user was not found!"));
        if (buyer == null || buyer.getUserStatus() == null || buyer.getRole() == null) {
            throw new Exception("This user is invalid, please check user situation for more details!");
        }
    }
}
