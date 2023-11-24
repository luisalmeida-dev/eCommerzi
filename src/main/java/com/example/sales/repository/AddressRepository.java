package com.example.sales.repository;

import com.example.sales.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
    List<AddressEntity> findAllByUserId(Integer userId);

    AddressEntity findByUserIdAndId(Integer userId, Integer id);
    void deleteAllByUserId(Integer userId);
}