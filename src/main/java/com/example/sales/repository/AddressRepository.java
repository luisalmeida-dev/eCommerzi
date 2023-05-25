package com.example.sales.repository;

import com.example.sales.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserId(Long userId);

    AddressEntity findByUserIdAndZipcode(Long userId, String zipcode);
}
