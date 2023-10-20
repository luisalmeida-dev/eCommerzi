package com.example.sales.service;

import com.example.sales.auth.service.TokenService;
import com.example.sales.dto.request.AddressRequestDTO;
import com.example.sales.dto.request.UserRequestDTO;
import com.example.sales.dto.request.UserUpdateRequestDTO;
import com.example.sales.dto.response.AddressResponseDTO;
import com.example.sales.dto.response.UserResponseDTO;
import com.example.sales.mapper.UserMapper;
import com.example.sales.model.AddressEntity;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.AddressRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CardService cardService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    public void createUser(UserRequestDTO request) throws Exception {
        if (userRepository.findByLogin(request.getLogin()) == null) {
            LocalDateTime dateTime = LocalDateTime.now();
            UserEntity userEntity = userMapper.toUserEntity(request);
            userEntity.setRegistrationDate(dateTime);
            userRepository.save(userEntity);
        } else {
            throw new Exception("User already Registered");
        }
    }

    public UserResponseDTO getUserByLogin(String authorization) throws Exception {
        UserEntity user = userRepository.findByLogin(tokenService.decodeToken(authorization).getSubject());
        if (user != null) {
            return userMapper.toUserResponseDTO(user);
        } else {
            throw new Exception("User doesn't exists!");
        }
    }

    public void updateUser(String authorization, UserUpdateRequestDTO request) throws Exception {
        UserEntity user = userRepository.findByLogin(tokenService.decodeToken(authorization).getSubject());
        if (user != null) {
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setPhone(request.getPhone());
            userRepository.save(user);
        } else {
            throw new Exception("User doesn't exists!");
        }
    }

    public void deleteUser(String authorization) {
        UserEntity user = userRepository.findByLogin(tokenService.decodeToken(authorization).getSubject());
        if (user != null) {
            delete(user.getId());
            userRepository.delete(user);
        } else {
            throw new UsernameNotFoundException("user doesn't exists!");
        }
    }

    public void addAddress(String authorization, AddressRequestDTO request) {
        AddressEntity address = userMapper.addressRequestDTOtoEntity(request);
        address.setUserId(tokenService.decodeToken(authorization).getClaim("userid").asLong());
        addressRepository.save(address);
    }

    public List<AddressResponseDTO> getAllAddressesByUser(String authorization) throws Exception {
        List<AddressEntity> addressList = addressRepository.findAllByUserId(tokenService.decodeToken(authorization).getClaim("userid").asLong());
        if (!addressList.isEmpty()) {
            return addressList.stream()
                    .map(userMapper::addressEntityToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new Exception("No address is registered to this user!");
        }
    }

    public AddressResponseDTO getAddressByUserAndId(String authorization, Long id) throws Exception {
        AddressEntity address = addressRepository.findByUserIdAndId(tokenService.decodeToken(authorization).getClaim("userid").asLong(), id);
        if (address != null) {
            return userMapper.addressEntityToDTO(address);
        } else {
            throw new Exception("This address is not registered!");
        }
    }

    public void deleteAddress(String authorization, Long id) throws Exception {
        Long userId = tokenService.decodeToken(authorization).getClaim("userid").asLong();
        AddressEntity address = addressRepository.findByUserIdAndId(userId, id);
        if (address != null) {
            addressRepository.delete(address);
        } else {
            throw new Exception("This address is not registered on our database!");
        }
    }

    private void delete(Long userId) {
        productService.deleteAllProducts(userId);
        cardService.deleteAllCards(userId);
        addressRepository.deleteAllByUserId(userId);
    }
}