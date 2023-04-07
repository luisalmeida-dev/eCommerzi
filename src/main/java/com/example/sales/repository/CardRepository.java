package com.example.sales.repository;

import com.example.sales.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    CardEntity findByUserIdAndCardNumber(Long id, String cardNumber);

    CardEntity findByIdAndUserId(Long cardId, Long userId);
}