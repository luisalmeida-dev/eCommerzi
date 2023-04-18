package com.example.sales.mapper;

import com.example.sales.dto.request.CardRequestDTO;
import com.example.sales.model.CardEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    private final ModelMapper modelMapper;

    public CardMapper() {
        modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CardRequestDTO.class, CardEntity.class).addMapping(CardRequestDTO::getExpirationDate, CardEntity::setExpirationDate);
    }

    public CardEntity toEntity(CardRequestDTO request) {
        return modelMapper.map(request, CardEntity.class);
    }
}
