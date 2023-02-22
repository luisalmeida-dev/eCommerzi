package com.example.sales.service;

import com.example.sales.dto.request.UserRequsetDTO;
import com.example.sales.dto.request.UserUpdateRequestDTO;
import com.example.sales.dto.response.UserResponseDTO;
import com.example.sales.model.UserStatusEntity;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.RoleRepository;
import com.example.sales.repository.StatusRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StatusRepository statusRepository;

    //TODO create a parser or mapper that transforms DTO's into entities vice versa
    public void createUser(UserRequsetDTO request) throws Exception {
        if (userRepository.findByCpf(request.getCpf()) == null) {
            //TODO fix dateTime not picking up correct time
            LocalDateTime dateTime = LocalDateTime.now();
            validateRoleAndStatus(request.getRoleId(), request.getStatusId());
            UserEntity userEntity = new UserEntity();
            userEntity.setCpf(request.getCpf());
            userEntity.setEmail(request.getEmail());
            userEntity.setName(request.getName());
            userEntity.setRoleId(request.getRoleId());
            userEntity.setStatusId(request.getStatusId());
            userEntity.setPassword(request.getPassword()); //TODO user password will be encrypted when authentication is implemented
            userEntity.setRegistrationDate(dateTime);
            userRepository.save(userEntity);
        } else {
            throw new Exception("User already Registered");
        }
    }

    public UserResponseDTO getUserByCpf (String cpf) throws Exception {
        UserEntity user = userRepository.findByCpf(cpf);
        if(user != null){
            //TODO see how parsers work to remove hardcoded sets.
            UserResponseDTO response = new UserResponseDTO();
            Optional<UserStatusEntity> status =  statusRepository.findById(user.getStatusId());
            status.ifPresent(statusUserEntity -> response.setStatus(statusUserEntity.getStatus()));
            response.setEmail(user.getEmail());
            response.setName(user.getName());
            response.setRegistrationDate(user.getRegistrationDate());
            return response;
        } else{
            throw new Exception("User doesn't exists!");
        }
    }

    public void updateUser(UserUpdateRequestDTO request) throws Exception {
        UserEntity user = userRepository.findByCpf(request.getCpf());
        if(user != null) {
            validateRoleAndStatus(user.getRoleId(), user.getStatusId());
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            userRepository.save(user);
        } else {
            throw new Exception("User doesn't exists!");
        }
    }

    public void deleteUser(String cpf) throws Exception {
        UserEntity user = userRepository.findByCpf(cpf);
        if(user != null) {
            validateRoleAndStatus(user.getRoleId(), user.getStatusId());
            userRepository.delete(user);
        }else {
            throw new UsernameNotFoundException("user doesn't exists!");
        }
    }


    private void validateRoleAndStatus(Long roleId, Long statusId) throws Exception {
        if(roleRepository.findById(roleId).isEmpty() || statusId == null){
            throw new Exception("Role or status isn't valid");
        }
    }
}
