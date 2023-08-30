package com.example.sales.repository;

import com.example.sales.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findAllByUserId(Long userId);

    CardEntity findByUserIdAndCardNumber(Long id, String cardNumber);

    CardEntity findByIdAndUserId(Long cardId, Long userId);

    void deleteAllByUserId(Long userId);
}