package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateProductVariantRequest;
import com.aeropelican.productservice.dto.request.UpdateProductVariantRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    // ==========================================
    // GET ALL VARIANTS
    // ==========================================

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariantResponse>>> getAllVariants() {

        return ResponseEntity.ok(
                ApiResponse.<List<ProductVariantResponse>>builder()
                        .success(true)
                        .message("Variants fetched successfully.")
                        .data(productVariantService.getAllVariants())
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // ==========================================
    // GET VARIANT BY ID
    // ==========================================

    @GetMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> getVariant(
            @PathVariable Long variantId) {

        return ResponseEntity.ok(
                ApiResponse.<ProductVariantResponse>builder()
                        .success(true)
                        .message("Variant fetched successfully.")
                        .data(productVariantService.getVariant(variantId))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // ==========================================
    // CREATE VARIANT
    // ==========================================

    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariantResponse>> createVariant(
            @Valid @RequestBody CreateProductVariantRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<ProductVariantResponse>builder()
                        .success(true)
                        .message("Variant created successfully.")
                        .data(productVariantService.createVariant(request))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // ==========================================
    // UPDATE VARIANT
    // ==========================================

    @PutMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> updateVariant(
            @PathVariable Long variantId,
            @Valid @RequestBody UpdateProductVariantRequest request) {

        return ResponseEntity.ok(
                ApiResponse.<ProductVariantResponse>builder()
                        .success(true)
                        .message("Variant updated successfully.")
                        .data(productVariantService.updateVariant(variantId, request))
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    // ==========================================
    // DELETE VARIANT
    // ==========================================

    @DeleteMapping("/{variantId}")
    public ResponseEntity<ApiResponse<String>> deleteVariant(
            @PathVariable Long variantId) {

        productVariantService.deleteVariant(variantId);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Variant deleted successfully.")
                        .data(null)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}