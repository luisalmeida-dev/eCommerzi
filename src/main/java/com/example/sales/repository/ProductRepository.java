package com.example.sales.repository;

import com.example.sales.Enum.CategoryEnum;
import com.example.sales.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findByUserIdAndSku(Integer userId, String sku);

    ProductEntity findByUserIdAndNameAndCategory(Integer userId, String name, CategoryEnum category);

    ProductEntity findByIdAndUserId(Integer id, Integer userId);

    List<ProductEntity> findAllByUserId(Integer userId);

    List<ProductEntity> findByIdIn(List<Integer> ids);

    void deleteAllByUserId(Integer userId);
}