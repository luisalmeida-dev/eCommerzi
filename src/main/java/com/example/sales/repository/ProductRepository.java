package com.example.sales.repository;

import com.example.sales.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public ProductEntity findBySku(String sku);

    public ProductEntity findByIdAndUserId(Long id, Long userId);

    public List<ProductEntity> findAllByUserId(Long userId);
}
