package com.example.sales.service;

import com.example.sales.dto.request.UserRequsetDTO;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void createUser(UserRequsetDTO request) throws Exception {
        if (userRepository.findByCpf(request.getCpf()) == null) {
            UserEntity userEntity = new UserEntity();
            //TODO create a parser or mapper that transforms DTO's into entities vice versa
            userEntity.setCpf(request.getCpf());
            userEntity.setEmail(request.getEmail());
            userEntity.setName(request.getName());
            userEntity.setStatusId(request.getStatusId());
            userEntity.setRoleId(request.getRoleId());
            userEntity.setPassword(request.getPassword()); //TODO user password will be encrypted when authentication is implemented
        } else {
            throw new Exception("User alredy Registered");
        }
    }
}
