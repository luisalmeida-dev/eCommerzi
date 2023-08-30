package com.example.sales.repository;

import com.example.sales.model.DiscountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
    Boolean existsByNameAndUserId(String name, Long userId);
    DiscountEntity findByIdAndUserId(Long discountId, Long userId);
    List<DiscountEntity> findAllByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
