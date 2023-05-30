package com.example.sales.mapper;

import com.example.sales.dto.request.DiscountRequestDTO;
import com.example.sales.dto.request.DiscountUpdateRequestDTO;
import com.example.sales.dto.response.DiscountResponseDTO;
import com.example.sales.model.DiscountEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {
    private final ModelMapper modelMapper;

    public DiscountMapper() {
        modelMapper = new ModelMapper();
    }

    public DiscountEntity discountRequestDTOtoEntity(DiscountRequestDTO request) {
        return modelMapper.map(request, DiscountEntity.class);
    }

    public DiscountResponseDTO discountEntitytoResponseDTO(DiscountEntity entity) {
        return modelMapper.map(entity, DiscountResponseDTO.class);
    }

    public void updateRequestDTOtoEntity(DiscountUpdateRequestDTO request, DiscountEntity entity) {
        modelMapper.map(request, entity);
    }
}
