package com.example.sales.service;

import com.example.sales.dto.request.OrderRequestDTO;
import com.example.sales.dto.response.OrderResponseDTO;
import com.example.sales.mapper.OrderMapper;
import com.example.sales.model.OrderEntity;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.OrderRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    public void createOrder(OrderRequestDTO request) throws Exception { //TODO create validation for stores, carriers, products, payments
        validateUser(request.getBuyerId());
        OrderEntity order = orderMapper.orderRequestToEntity(request);
        orderRepository.save(order);
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

    private void validateUser(Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("This user was not found!"));
        if (user == null || user.getUserStatus() == null || user.getRole() == null) {
            throw new Exception("This user is invalid, please check user situation for more details!");
        }
    }
}
