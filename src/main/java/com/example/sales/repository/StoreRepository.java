package com.example.sales.repository;

import com.example.sales.model.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    StoreEntity findByCnpj(String cnpj);
}
