package com.example.sales.service;

import com.example.sales.Enum.RolesEnum;
import com.example.sales.Enum.UserStatusEnum;
import com.example.sales.dto.request.UserRequestDTO;
import com.example.sales.dto.request.UserUpdateRequestDTO;
import com.example.sales.dto.response.UserResponseDTO;
import com.example.sales.mapper.UserMapper;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public void createUser(UserRequestDTO request) throws Exception {
        if (userRepository.findByLogin(request.getLogin()) == null) {
            LocalDateTime dateTime = LocalDateTime.now();
            validateRoleAndStatus(request.getRole().name(), request.getUserStatus().name());
            UserEntity userEntity = userMapper.userRequestDTOtoEntity(request);
            userEntity.setRegistrationDate(dateTime);
            userRepository.save(userEntity);
        } else {
            throw new Exception("User already Registered");
        }
    }

    public UserResponseDTO getUserByLogin(String login) throws Exception {
        UserEntity user = userRepository.findByLogin(login);
        if (user != null) {
            return userMapper.toUserResponseDTO(user);
        } else {
            throw new Exception("User doesn't exists!");
        }
    }

    public void updateUser(UserUpdateRequestDTO request) throws Exception {
        UserEntity user = userRepository.findByLogin(request.getLogin());
        if (user != null) {
            validateRoleAndStatus(user.getRole(), user.getUserStatus());
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setPhone(request.getPhone());
            userRepository.save(user);
        } else {
            throw new Exception("User doesn't exists!");
        }
    }

    public void deleteUser(String login) throws Exception {
        UserEntity user = userRepository.findByLogin(login);
        if (user != null) {
            validateRoleAndStatus(user.getRole(), user.getUserStatus());
            userRepository.delete(user);
        } else {
            throw new UsernameNotFoundException("user doesn't exists!");
        }
    }

    //TODO corrigir resposta quando o status ou role passado nao existe (esta retornando um 404)
    private void validateRoleAndStatus(String role, String userStatus) throws Exception { //TODO vai ser subsittuida por uma authorization com spring security
        if (userStatus.equals(UserStatusEnum.ACTIVE.name())) {
            for (RolesEnum rolesEnum : RolesEnum.values()) {
                if (rolesEnum.name().equals(role)) {
                    return;
                }
            }
        } else {
            throw new Exception("The user status isn't valid!");
        }
    }
}