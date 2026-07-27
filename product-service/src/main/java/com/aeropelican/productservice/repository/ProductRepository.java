package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // 🟢 Search products by category
    List<Product> findByCategory(String category);

}