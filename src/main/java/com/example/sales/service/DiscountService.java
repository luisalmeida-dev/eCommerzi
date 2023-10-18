package com.example.sales.service;

import com.example.sales.auth.service.TokenService;
import com.example.sales.dto.request.DiscountRequestDTO;
import com.example.sales.dto.request.DiscountUpdateRequestDTO;
import com.example.sales.dto.response.DiscountResponseDTO;
import com.example.sales.mapper.DiscountMapper;
import com.example.sales.model.DiscountEntity;
import com.example.sales.repository.DiscountRepository;
import com.example.sales.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscountMapper discountMapper;

    @Autowired
    private TokenService tokenService;

    public void createDiscount(String authorization, DiscountRequestDTO request) throws Exception {
        Long userId = tokenService.decodeToken(authorization).getClaim("userid").asLong();
        if (!discountRepository.existsByNameAndUserId(request.getName(), userId)) {
            DiscountEntity discount = discountMapper.discountRequestDTOtoEntity(request);
            discount.setUserId(userId);
            discountRepository.save(discount);
        } else {
            throw new Exception("This discount is already registered! Please verify on ");
        }
    }

    public List<DiscountResponseDTO> getAllDiscountsByUser(String authorization) throws Exception {
        Long userId = tokenService.decodeToken(authorization).getClaim("userid").asLong();
        if (userRepository.existsById(userId)) {
            List<DiscountEntity> discountList = discountRepository.findAllByUserId(userId);
            return discountList.stream()
                    .map(discountMapper::discountEntitytoResponseDTO)
                    .collect(Collectors.toList());
        } else {
            throw new Exception("User Not Found!");
        }
    }

    public void deleteDiscountById(String authorization, Long discountId) {
        DiscountEntity discount = discountRepository.findByIdAndUserId(tokenService.decodeToken(authorization).getClaim("userid").asLong(), discountId);
        if (discount != null) {
            discountRepository.delete(discount);
        } else {
            throw new EntityNotFoundException("The discount doesn't exists!");
        }
    }

    public void updateDiscountById(String authorization, DiscountUpdateRequestDTO request, Long discountId) {
        Long userId = tokenService.decodeToken(authorization).getClaim("userid").asLong();
        DiscountEntity discount = discountRepository.findByIdAndUserId(userId, discountId);
        if (discount != null) {
            discountMapper.updateRequestDTOtoEntity(request, discount);
            discount.setUserId(userId);
            discountRepository.save(discount);
        } else {
            throw new EntityNotFoundException("Discount Not Found!");
        }
    }

    public void deleteAllDiscounts(Long userId) {
        discountRepository.deleteAllByUserId(userId);
    }
}
