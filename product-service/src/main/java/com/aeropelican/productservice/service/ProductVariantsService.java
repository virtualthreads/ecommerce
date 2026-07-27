package com.aeropelican.productservice.service;
import com.aeropelican.productservice.dto.CreateProduct_VariantsRequest;
import com.aeropelican.productservice.dto.UpdateProduct_Variants;
import com.aeropelican.productservice.entity.Product_Variants;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class ProductVariantsService {

        private final ProductVariantsRepository productVariantsRepository;

        public Product_Variants getProductVariant(Integer variantId) {

            return productVariantsRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("Product Variant not found"));
        }

        public Product_Variants createProductVariant(CreateProduct_VariantsRequest request) {
            Product_Variants variant = new Product_Variants();
            variant.setProduct_id(request.getProduct_id());
            variant.setSku(request.getSku());
            variant.setColor(request.getColor());
            variant.setStorage_capacity(request.getStorage_capacity());
            variant.setPrice(request.getPrice());
            Timestamp now = new Timestamp(System.currentTimeMillis());
            variant.setCreated_at(now);
            variant.setUpdated_at(now);

            return productVariantsRepository.save(variant);
        }

        public Product_Variants updateProductVariant(
                Integer variantId,
                UpdateProduct_Variants request) {
            Product_Variants variant = productVariantsRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("Product Variant not found"));
            variant.setProduct_id(request.getProduct_id());
            variant.setSku(request.getSku());
            variant.setColor(request.getColor());
            variant.setStorage_capacity(request.getStorage_capacity());
            variant.setPrice(request.getPrice());

            return productVariantsRepository.save(variant);
        }

        public Product_Variants deleteProductVariant(Integer variantId) {

            Product_Variants variant = productVariantsRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("Product Variant not found"));

            productVariantsRepository.delete(variant);

            return variant;
        }

}
