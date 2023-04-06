package com.example.sales.service;

import com.example.sales.dto.response.CardResponseDTO;
import com.example.sales.model.CardEntity;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.CardRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    public CardResponseDTO getCardById(Long id) throws Exception {
        CardEntity card = cardRepository.findById(id).orElseThrow(() -> new Exception("this card doesn't exists!"));
        validateUser(card.getUserId());
        CardResponseDTO response = new CardResponseDTO();
        response.setCardNickname(card.getCardNickname());
        response.setName(card.getName());
        response.setCardNumber(card.getCardNumber());
        return response;
    }

    //TODO createCard, deleteCard, updateCard

    private void validateUser(Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("This user doesn't match with the card!"));
        if (user == null || user.getStatusId() == null || user.getRoleId() != 1) {
            throw new Exception("this user is invalid, please check user situation for more details!");
        }
    }
}
