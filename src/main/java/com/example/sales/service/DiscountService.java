package com.example.sales.service;

import com.example.sales.Enum.RolesEnum;
import com.example.sales.dto.request.DiscountRequestDTO;
import com.example.sales.dto.request.DiscountUpdateRequestDTO;
import com.example.sales.dto.response.DiscountResponseDTO;
import com.example.sales.mapper.DiscountMapper;
import com.example.sales.model.DiscountEntity;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.DiscountRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public void createDiscount(DiscountRequestDTO request) throws Exception { //TODO adicionar validacao de usuario com jwt
        validateUser(request.getUserId());
        if (!discountRepository.existsByNameAndUserId(request.getName(), request.getUserId())) {
            DiscountEntity discount = discountMapper.discountRequestDTOtoEntity(request);
            discountRepository.save(discount);
        } else {
            throw new Exception("This discount is already registered! Please verify on ");
        }
    }

    public List<DiscountResponseDTO> getAllDiscountsByUser(Long userId) throws Exception {
        validateUser(userId);
        if (userRepository.existsById(userId)) {
            List<DiscountEntity> discountList = discountRepository.findAllByUserId(userId);
            return discountList.stream()
                    .map(discountMapper::discountEntitytoResponseDTO)
                    .collect(Collectors.toList());
        } else {
            throw new Exception("User Not Found!");
        }
    }

    public void deleteDiscountById(Long discountId) {
        DiscountEntity discount = discountRepository.findById(discountId).orElseThrow(() -> new EntityNotFoundException("Discount Not Found!"));
        discountRepository.delete(discount);
    }

    //TODO Corrigir: Quando cadastramos um disconto igual, so que para outro usuario ele tbm esta bloqueando. (Isso vai ser corrigido quando tivermos o userId no jwt)
    public void updateDiscountById(DiscountUpdateRequestDTO request, Long discountId) {
        DiscountEntity discount = discountRepository.findById(discountId).orElseThrow(() -> new EntityNotFoundException("Discount Not Found!"));
        discountMapper.updateRequestDTOtoEntity(request, discount);
        discountRepository.save(discount);
    }

    public void deleteAllDiscounts(Long userId) throws Exception {
        validateUser(userId);
        discountRepository.deleteAllByUserId(userId);
    }

    public void validateUser(Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("User Not Found!"));
        if (!user.getRole().equals(RolesEnum.STORE)) {
            throw new Exception("This user can't create a discount");
        }
    }
}
