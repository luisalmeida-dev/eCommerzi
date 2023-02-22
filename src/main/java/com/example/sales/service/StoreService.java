package com.example.sales.service;

import com.example.sales.dto.request.StoreRequestDTO;
import com.example.sales.repository.StoreRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    public void createStore(StoreRequestDTO request) throws Exception {
        if () {

        } else {

        }
    }

    private void validateStoreAndUser(String cnpj, Long userId) throws Exception {
        if (storeRepository.findByCnpj(cnpj) != null || userRepository.findById(userId).isEmpty()) {
            throw new Exception("Store already registered or user");
        }
    }
}