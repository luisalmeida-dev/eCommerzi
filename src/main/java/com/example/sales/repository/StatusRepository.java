package com.example.sales.repository;

import com.example.sales.model.StatusUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<StatusUserEntity, Long> {
}
