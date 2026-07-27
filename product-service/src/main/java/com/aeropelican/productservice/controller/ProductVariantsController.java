package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.CreateProduct_VariantsRequest;
import com.aeropelican.productservice.dto.UpdateProduct_Variants;
import com.aeropelican.productservice.dto.response.APIResponse;
import com.aeropelican.productservice.entity.Category;
import com.aeropelican.productservice.entity.Product_Variants;
import com.aeropelican.productservice.service.ProductVariantsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/product-variants")
@RequiredArgsConstructor
public class ProductVariantsController {

    private final ProductVariantsService productVariantsService;

    @GetMapping("/{variant_id}")
    public ResponseEntity<APIResponse<Product_Variants>> getProductVariant(
            @PathVariable Integer variant_id) {

        Product_Variants product_variants = productVariantsService.getProductVariant(variant_id);

        APIResponse<Product_Variants> response = APIResponse.<Product_Variants>builder()
                .data(product_variants)
                .message("Product Variant fetched successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Product_Variants>> createProductVariant(
            @RequestBody CreateProduct_VariantsRequest request) {

        Product_Variants  product_variants = productVariantsService.createProductVariant(request);

        APIResponse<Product_Variants> response = APIResponse.<Product_Variants>builder()
                .data(product_variants)
                .message("Product Variant created successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{variant_id}")
    public ResponseEntity<APIResponse<Product_Variants>> updateProductVariant(
            @PathVariable Integer variant_id,
            @RequestBody UpdateProduct_Variants request) {

        Product_Variants product_variants = productVariantsService.updateProductVariant(variant_id, request);

        APIResponse<Product_Variants> response = APIResponse.<Product_Variants>builder()
                .data(product_variants)
                .message("Product Variant updated successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{variant_id}")
    public ResponseEntity<APIResponse<Product_Variants>> deleteProductVariant(
            @PathVariable Integer variant_id) {

        Product_Variants product_variants = productVariantsService.deleteProductVariant(variant_id);

        APIResponse<Product_Variants> response = APIResponse.<Product_Variants>builder()
                .data(product_variants)
                .message("Product Variant deleted successfully")
                .success(true)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}