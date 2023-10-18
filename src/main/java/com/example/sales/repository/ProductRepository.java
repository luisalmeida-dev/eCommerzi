package com.example.sales.repository;

import com.example.sales.Enum.CategoryEnum;
import com.example.sales.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findByUserIdAndSku(Long userId, String sku);

    ProductEntity findByUserIdAndNameAndCategory(Long userId, String name, CategoryEnum category);

    ProductEntity findByIdAndUserId(Long id, Long userId);

    List<ProductEntity> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
