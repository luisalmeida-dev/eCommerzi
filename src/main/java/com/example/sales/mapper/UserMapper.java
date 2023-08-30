package com.example.sales.mapper;

import com.example.sales.dto.request.AddressRequestDTO;
import com.example.sales.dto.request.UserRequestDTO;
import com.example.sales.dto.response.AddressResponseDTO;
import com.example.sales.dto.response.UserResponseDTO;
import com.example.sales.model.AddressEntity;
import com.example.sales.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper() {
        modelMapper = new ModelMapper();
    }

    public UserResponseDTO toUserResponseDTO(UserEntity entity) {
        return modelMapper.map(entity, UserResponseDTO.class);
    }

    public UserEntity toUserEntity(UserRequestDTO request){
        return modelMapper.map(request, UserEntity.class);
    }

    public AddressEntity addressRequestDTOtoEntity(AddressRequestDTO request){
        return modelMapper.map(request, AddressEntity.class);
    }

    public AddressResponseDTO addressEntityToDTO(AddressEntity entity){
        return modelMapper.map(entity, AddressResponseDTO.class);
    }
}
