package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateProductVariantRequest;
import com.aeropelican.productservice.dto.request.UpdateProductVariantRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.entity.ProductVariant;
import com.aeropelican.productservice.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    // ============================
    // GET ALL VARIANTS
    // ============================

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariant>>> getAllVariants() {

        List<ProductVariant> variants = productVariantService.getAllVariants();

        ApiResponse<List<ProductVariant>> response =
                ApiResponse.<List<ProductVariant>>builder()
                        .success(true)
                        .message("Variants fetched successfully")
                        .data(variants)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // ============================
    // GET VARIANT BY ID
    // ============================

    @GetMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariant>> getVariant(
            @PathVariable Long variantId) {

        ProductVariant variant = productVariantService.getVariant(variantId);

        ApiResponse<ProductVariant> response =
                ApiResponse.<ProductVariant>builder()
                        .success(variant != null)
                        .message(variant != null ?
                                "Variant fetched successfully"
                                : "Variant not found")
                        .data(variant)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // ============================
    // CREATE VARIANT
    // ============================

    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariant>> createVariant(
            @RequestBody CreateProductVariantRequest request) {

        ProductVariant variant = productVariantService.createVariant(request);

        ApiResponse<ProductVariant> response =
                ApiResponse.<ProductVariant>builder()
                        .success(variant != null)
                        .message(variant != null ?
                                "Variant created successfully"
                                : "Product not found")
                        .data(variant)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // ============================
    // UPDATE VARIANT
    // ============================

    @PutMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariant>> updateVariant(
            @PathVariable Long variantId,
            @RequestBody UpdateProductVariantRequest request) {

        ProductVariant variant =
                productVariantService.updateVariant(variantId, request);

        ApiResponse<ProductVariant> response =
                ApiResponse.<ProductVariant>builder()
                        .success(variant != null)
                        .message(variant != null ?
                                "Variant updated successfully"
                                : "Variant not found")
                        .data(variant)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

    // ============================
    // DELETE VARIANT
    // ============================

    @DeleteMapping("/{variantId}")
    public ResponseEntity<ApiResponse<String>> deleteVariant(
            @PathVariable Long variantId) {

        boolean deleted = productVariantService.deleteVariant(variantId);

        ApiResponse<String> response =
                ApiResponse.<String>builder()
                        .success(deleted)
                        .message(deleted ?
                                "Variant deleted successfully"
                                : "Variant not found")
                        .data(deleted ?
                                "Variant deleted successfully"
                                : "No Variant Found")
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(response);
    }

}