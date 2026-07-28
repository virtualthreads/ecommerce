package com.aeropelican.productservice.service;
import com.aeropelican.productservice.dto.request.CreateProduct_VariantsRequest;
import com.aeropelican.productservice.dto.request.UpdateProduct_Variants;
import com.aeropelican.productservice.dto.response.Product_variantsResponse;
import com.aeropelican.productservice.entity.Product_Variants;
import com.aeropelican.productservice.repository.ProductVariantsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductVariantsService {
        private final ProductVariantsRepository productVariantsRepository;
        public Product_Variants getProductVariant(Integer variantId) {

            return productVariantsRepository.findById(variantId)
                    .orElseThrow(() -> new RuntimeException("Product Variant not found"));
        }
    public List<Product_variantsResponse> listProductVariants() {
        List<Product_Variants> results = productVariantsRepository.findAll();
        List<Product_variantsResponse> response = new ArrayList<>();

        for (Product_Variants variant : results) {

            Product_variantsResponse variantResponse = Product_variantsResponse.builder()
                    .variant_id(variant.getVariant_id())
                    .product_id(variant.getProduct_id())
                    .sku(variant.getSku())
                    .color(variant.getColor())
                    .storage_capacity(variant.getStorage_capacity())
                    .price(variant.getPrice())
                    .is_active(variant.is_active())
                    .created_at(variant.getCreated_at())
                    .updated_at(variant.getUpdated_at())
                    .build();

            response.add(variantResponse);
        }

        return response;
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
