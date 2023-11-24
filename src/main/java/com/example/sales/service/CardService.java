package com.example.sales.service;

import com.example.sales.auth.service.TokenService;
import com.example.sales.dto.request.CardRequestDTO;
import com.example.sales.dto.request.CardUpdateRequestDTO;
import com.example.sales.dto.response.CardResponseDTO;
import com.example.sales.mapper.CardMapper;
import com.example.sales.model.CardEntity;
import com.example.sales.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TokenService tokenService;

    public List<CardResponseDTO> getAllCardsByUser(String authorization) throws Exception {
        List<CardEntity> cardList = cardRepository.findAllByUserId(tokenService.decodeToken(authorization).getClaim("userid").asInt());
        if (!cardList.isEmpty()) {
            return cardList.stream()
                    .map(cardMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            throw new Exception("There is no card registered to this user!");
        }
    }

    public CardResponseDTO getCardById(Integer id) throws Exception {
        CardEntity card = cardRepository.findById(id).orElseThrow(() -> new Exception("this card doesn't exists!"));
        return cardMapper.toDTO(card);
    }

    public void createCard(String authorization, CardRequestDTO request) throws Exception {
        Integer userId = tokenService.decodeToken(authorization).getClaim("userid").asInt();
        CardEntity card = cardRepository.findByUserIdAndCardNumber(userId, request.getCardNumber());
        if (card == null) {
            card = cardMapper.toEntity(request);
            card.setExpirationDate(request.getExpirationDate());
            card.setUserId(userId);
            cardRepository.save(card);
        } else {
            throw new Exception("This card is already registered!");
        }
    }

    public void deleteCard(String authorization, Integer cardId) throws Exception {
        CardEntity card = cardRepository.findByIdAndUserId(cardId, tokenService.decodeToken(authorization).getClaim("userid").asInt());
        if (card != null) {
            cardRepository.delete(card);
        } else {
            throw new Exception("The card you're trying to delete doesnt' exist!");
        }
    }

    public void updateCard(String authorization, CardUpdateRequestDTO requestDTO, Integer cardId) throws Exception {
        CardEntity card = cardRepository.findByIdAndUserId(cardId, tokenService.decodeToken(authorization).getClaim("userid").asInt());
        if (card != null) {
            card.setCardNickname(requestDTO.getCardNickname());
            card.setName(requestDTO.getName());
            card.setExpirationDate(requestDTO.getExpirationDate());
            cardRepository.save(card);
        } else {
            throw new Exception("This card doesn't exist!");
        }
    }

    public void deleteAllCards(Integer userId) {
        cardRepository.deleteAllByUserId(userId);
    }
}