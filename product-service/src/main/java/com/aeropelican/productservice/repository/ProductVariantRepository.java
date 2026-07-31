package com.aeropelican.productservice.repository;

import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    // Get all variants of a product
    List<ProductVariant> findByProduct(Product product);

    // Get all active variants
    List<ProductVariant> findByIsActive(Boolean isActive);

    // Search by SKU
    ProductVariant findBySku(String sku);

}