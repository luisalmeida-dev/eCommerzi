package com.example.sales.repository;

import com.example.sales.model.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<Long, UserStatusEntity> {
}
