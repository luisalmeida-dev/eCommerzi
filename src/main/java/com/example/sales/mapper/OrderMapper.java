package com.example.sales.mapper;

import com.example.sales.dto.request.OrderRequestDTO;
import com.example.sales.dto.response.OrderResponseDTO;
import com.example.sales.model.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    private final ModelMapper modelMapper;

    public OrderMapper() {
        modelMapper = new ModelMapper();
    }

    public OrderEntity orderRequestToEntity(OrderRequestDTO requestDTO) {
        return modelMapper.map(requestDTO, OrderEntity.class);
    }

    public OrderResponseDTO orderEntityToDTO(OrderEntity entity) {

        return modelMapper.map(entity, OrderResponseDTO.class);
    }
}
