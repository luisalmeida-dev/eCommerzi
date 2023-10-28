package com.example.sales.service;

import com.example.sales.Enum.OrderStatusEnum;
import com.example.sales.auth.service.TokenService;
import com.example.sales.dto.request.OrderRequestDTO;
import com.example.sales.dto.response.OrderResponseDTO;
import com.example.sales.dto.response.ProductResponseDTO;
import com.example.sales.mapper.OrderMapper;
import com.example.sales.mapper.ProductMapper;
import com.example.sales.model.OrderEntity;
import com.example.sales.model.ProductEntity;
import com.example.sales.repository.AddressRepository;
import com.example.sales.repository.OrderRepository;
import com.example.sales.repository.ProductRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProductMapper productMapper;

    private final Random random = new Random();

    @Transactional
    public void createOrder(String authorization, OrderRequestDTO request) throws Exception { //TODO create validation for stores, carriers, products, payments
        List<ProductEntity> productList = productRepository.findAllById(request.getProductIdList());
        BigDecimal productTotalPrice = new BigDecimal(0);
        for (ProductEntity product : productList) {
            if (product.getQuantity() >= request.getQuantity()) {
                product.setQuantity(product.getQuantity() - request.getQuantity());
                productTotalPrice = productTotalPrice.add(product.getPrice());
                productRepository.save(product);
            } else {
                throw new Exception("There's only " + product.getQuantity() + "units available");
            }
        }
        BigDecimal shippingPrice = carrierService.shippingPrice(productTotalPrice, request.getCarrierId());

        OrderEntity order = orderMapper.orderRequestToEntity(request);
        order.setProductIdList(productIdListToString(request.getProductIdList()));
        order.setShippingPrice(shippingPrice);
        order.setTotal(productTotalPrice.add(order.getShippingPrice()));
        order.setBuyerId(tokenService.decodeToken(authorization).getClaim("userid").asLong());
        order.setOrderStatus(getRandomStatus());
        order.setTrackingNumber(carrierService.generateTrackingNumber(authorization));
        order.setRegistrationDate(Date.from(Instant.now()));
        order.setDeliveryDate(LocalDateTime.now().plusDays(6));
        order.setOrderCode(generateOrderCode());


        orderRepository.save(order);
    }

    public List<OrderResponseDTO> getOrdersByUser(String authorization) throws Exception {
        List<OrderEntity> orderList = orderRepository.getAllByBuyerId(tokenService.decodeToken(authorization).getClaim("userid").asLong());
        if (!orderList.isEmpty()) {
            return orderList.stream()
                    .map(orderEntity -> {
                        OrderResponseDTO response = orderMapper.orderEntityToDTO(orderEntity);
                        response.setProductList(productIdStringToList(orderEntity.getProductIdList()));
                        response.setStoreName((userRepository.findById(orderEntity.getStoreId()).get().getName()));
                        response.setCarrierName((userRepository.findById(orderEntity.getCarrierId()).get().getName()));
                        response.setUserAddress((addressRepository.findById(orderEntity.getBuyerId()).get().getCity()));

                        return response;
                    }).toList();
        } else {
            throw new Exception("You didn't ordered yet!");
        }
    }

    private OrderStatusEnum getRandomStatus() {
        OrderStatusEnum[] values = OrderStatusEnum.values();
        int index = random.nextInt(values.length);
        return values[index];
    }

    private long generateOrderCode() {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        return (rng.nextLong() % 90000000L) + 10000000L;
    }

    private String productIdListToString(List<Long> productList) {
        StringBuilder productIdsString = new StringBuilder();
        for (Long id : productList) {
            productIdsString.append(id.toString()).append(",");
        }
        return productIdsString.toString();
    }

    private List<ProductResponseDTO> productIdStringToList(String productList) {
        List<Long> list = Arrays.stream(productList.split(",")).map(s -> Long.parseLong(s.trim())).toList();
        List<ProductEntity> responseList = productRepository.findAllById(list);
        return responseList.stream()
                .map(productMapper::toProductResponseDTO)
                .toList();
    }
}
