package com.rizwan.product_service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Model, Long> {
    Optional<Model> findModelById(Long id);
}
