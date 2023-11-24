package com.example.sales.repository;

import com.example.sales.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Integer> {
    List<CardEntity> findAllByUserId(Integer userId);

    CardEntity findByUserIdAndCardNumber(Integer id, String cardNumber);

    CardEntity findByIdAndUserId(Integer cardId, Integer userId);

    void deleteAllByUserId(Integer userId);
}