package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.response.CreateProductVariantsRequest;
import com.aeropelican.productservice.dto.response.UpdateProductVariants;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductVariantsService {

    @Autowired
    private ProductVariantsRepository productVariantsRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductVariants saveVariant(CreateProductVariantsRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        ProductVariants variant = new ProductVariants();
        variant.setVariantName(request.getVariantName());
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        variant.setPrice(request.getPrice());

        return productVariantsRepository.save(variant);
    }
}