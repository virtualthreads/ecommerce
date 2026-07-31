package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find all products by category
    List<Product> findByCategory(Category category);

    // Find all active products
    List<Product> findByIsActive(Boolean isActive);

    // Search product by name
    List<Product> findByProductNameContainingIgnoreCase(String productName);

}