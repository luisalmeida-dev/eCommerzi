package com.example.sales.service;

import com.example.sales.dto.request.CardRequestDTO;
import com.example.sales.dto.request.CardUpdateRequestDTO;
import com.example.sales.dto.response.CardResponseDTO;
import com.example.sales.mapper.CardMapper;
import com.example.sales.model.CardEntity;
import com.example.sales.model.UserEntity;
import com.example.sales.repository.CardRepository;
import com.example.sales.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardMapper cardMapper;

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

    public void createCard(CardRequestDTO request) throws Exception {
        validateUser(request.getUserId());
        CardEntity card = cardRepository.findByUserIdAndCardNumber(request.getUserId(), request.getCardNumber());
        if (card == null) {
            card = cardMapper.toEntity(request);
            cardRepository.save(card);
        } else {
            throw new Exception("This card is already registered!");
        }
    }

    public void deleteCard(Long cardId, Long userId) throws Exception { //TODO userId will be find on token or something (user will not input it)
        validateUser(userId);
        CardEntity card = cardRepository.findByIdAndUserId(cardId, userId);
        if (card != null) {
            cardRepository.delete(card);
        } else {
            throw new Exception("The card you're trying to delete doesnt' exist!");
        }
    }

    public void updateCard(Long userId, CardUpdateRequestDTO requestDTO) throws Exception {
        validateUser(userId);
        CardEntity card = cardRepository.findByUserIdAndCardNumber(userId, requestDTO.getCardNumber());
        if (card != null) {
            card.setCardNickname(requestDTO.getCardNickname());
            cardRepository.save(card);
        } else {
            throw new Exception("This card doesn't exist!");
        }
    }


    private void validateUser(Long userId) throws Exception {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("This user was not found!"));
        if (user == null || user.getStatusId() == null || user.getRoleId() != 1) {
            throw new Exception("This user is invalid, please check user situation for more details!");
        }
    }
}
