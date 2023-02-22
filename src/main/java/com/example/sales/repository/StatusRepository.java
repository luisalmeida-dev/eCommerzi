package com.example.sales.repository;

import com.example.sales.model.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<UserStatusEntity, Long> {
}
