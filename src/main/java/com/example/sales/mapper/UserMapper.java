package com.example.sales.mapper;

import com.example.sales.dto.request.UserRequestDTO;
import com.example.sales.dto.response.UserResponseDTO;
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

    public UserEntity toUserEntity(UserResponseDTO request) {
        return modelMapper.map(request, UserEntity.class);
    }

    public UserEntity userRequestDTOtoEntity(UserRequestDTO request){
        return modelMapper.map(request, UserEntity.class);
    }
}
