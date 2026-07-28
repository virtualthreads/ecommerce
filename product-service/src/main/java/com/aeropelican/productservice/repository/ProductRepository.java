package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Check if product name exists (for duplicate checks)
    boolean existsByProductName(String productName);

    // Search products by name (case-insensitive substring match)
    List<Product> findByProductNameContainingIgnoreCase(String name);
}