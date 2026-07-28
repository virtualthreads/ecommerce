package com.aeropelican.productservice.service;

import com.aeropelican.productservice.dto.request.CreateProductVariantsRequest;
import com.aeropelican.productservice.dto.request.UpdateProductVariantsRequest;
import com.aeropelican.productservice.entity.Product;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.repository.ProductRepository;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantsService {

    @Autowired
    private ProductVariantsRepository productVariantsRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductVariants saveVariant(CreateProductVariantsRequest request) {

        ProductVariants variant = new ProductVariants();

        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product Not Found"));
            variant.setProduct(product);
        }

        variant.setVariantName(request.getVariantName());
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        variant.setPrice(request.getPrice());

        return productVariantsRepository.save(variant);
    }

    public List<ProductVariants> getAllVariants() {
        return productVariantsRepository.findAll();
    }

    public ProductVariants getVariantById(Long id) {

        return productVariantsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Variant with ID "  + id + " not found"));
    }

    public ProductVariants updateVariant(Long id, UpdateProductVariantsRequest request) {

        ProductVariants variant = productVariantsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Variant Not Found"));

        if (request.getVariantName() != null) {
            variant.setVariantName(request.getVariantName());
        }
        variant.setColor(request.getColor());
        variant.setSize(request.getSize());
        variant.setPrice(request.getPrice());

        return productVariantsRepository.save(variant);
    }

    public String deleteVariant(Long id) {

        productVariantsRepository.deleteById(id);

        return "Product Variant Deleted Successfully";
    }

}