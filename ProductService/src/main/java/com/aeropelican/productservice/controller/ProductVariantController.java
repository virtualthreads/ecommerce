package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.ProductVariantResponse;
import com.aeropelican.productservice.service.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product-variants")
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductVariantService productVariantService;

    // GET ALL PRODUCT VARIANTS
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductVariantResponse>>> getAllProductVariants() {

        List<ProductVariantResponse> variants =
                productVariantService.listProductVariants();

        ApiResponse<List<ProductVariantResponse>> apiResponse =
                ApiResponse.<List<ProductVariantResponse>>builder()
                        .data(variants)
                        .message("Product variants fetched successfully")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(apiResponse);
    }

    // GET PRODUCT VARIANT BY ID
    @GetMapping("/{variantId}")
    public ResponseEntity<ApiResponse<ProductVariantResponse>> getProductVariant(
            @PathVariable("variantId") Long variantId) {

        ProductVariantResponse variant =
                productVariantService.getProductVariant(variantId);

        ApiResponse<ProductVariantResponse> apiResponse =
                ApiResponse.<ProductVariantResponse>builder()
                        .data(variant)
                        .message("Product variant details fetched successfully")
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.ok(apiResponse);
    }
}