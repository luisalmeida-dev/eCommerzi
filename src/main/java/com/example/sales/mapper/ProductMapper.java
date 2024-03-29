package com.example.sales.mapper;

import com.example.sales.dto.request.ProductRequestDTO;
import com.example.sales.dto.request.ProductUpdateRequestDTO;
import com.example.sales.dto.response.ProductResponseDTO;
import com.example.sales.model.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    private final ModelMapper modelMapper;

    public ProductMapper() {
        modelMapper = new ModelMapper();
    }

    public ProductEntity toProductEntity(ProductRequestDTO request) {
        return modelMapper.map(request, ProductEntity.class);
    }

    public ProductResponseDTO toProductResponseDTO(ProductEntity entity) {
        return modelMapper.map(entity, ProductResponseDTO.class);
    }

    public ProductEntity productUpdateDTOtoEntity(ProductUpdateRequestDTO request) {
        return modelMapper.map(request, ProductEntity.class);
    }
}