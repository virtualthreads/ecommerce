package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateProductVariantsRequest;
import com.aeropelican.productservice.dto.request.UpdateProductVariantsRequest;
import com.aeropelican.productservice.dto.response.ApiResponse;
import com.aeropelican.productservice.dto.response.PageResponse;
import com.aeropelican.productservice.dto.response.ProductVariantsResponse;
import com.aeropelican.productservice.service.ProductVariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/variants")
public class ProductVariantsController {

    @Autowired
    private ProductVariantsService productVariantsService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductVariantsResponse>> createVariant(@RequestBody CreateProductVariantsRequest request) {
        return new ResponseEntity<>(productVariantsService.saveVariant(request), HttpStatus.CREATED);
    }

    // URL: GET /api/v1/variants/0/10
    @GetMapping("/{page}/{size}")
    public ResponseEntity<ApiResponse<PageResponse<ProductVariantsResponse>>> getAllVariants(
            @PathVariable int page,
            @PathVariable int size) {
        return ResponseEntity.ok(productVariantsService.getAllVariants(page, size, "variantId", "ASC"));
    }

    // URL: GET /api/v1/variants/0/10/variantName
    @GetMapping("/{page}/{size}/{sortBy}")
    public ResponseEntity<ApiResponse<PageResponse<ProductVariantsResponse>>> getAllVariantsWithSort(
            @PathVariable int page,
            @PathVariable int size,
            @PathVariable String sortBy) {
        return ResponseEntity.ok(productVariantsService.getAllVariants(page, size, sortBy, "ASC"));
    }

    // URL: GET /api/v1/variants/0/10/variantName/desc
    @GetMapping("/{page}/{size}/{sortBy}/{sortDirection}")
    public ResponseEntity<ApiResponse<PageResponse<ProductVariantsResponse>>> getAllVariantsWithSortDir(
            @PathVariable int page,
            @PathVariable int size,
            @PathVariable String sortBy,
            @PathVariable String sortDirection) {
        return ResponseEntity.ok(productVariantsService.getAllVariants(page, size, sortBy, sortDirection));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<ProductVariantsResponse>> getVariantById(@PathVariable Long id) {
        return ResponseEntity.ok(productVariantsService.getVariantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductVariantsResponse>> updateVariant(@PathVariable Long id, @RequestBody UpdateProductVariantsRequest request) {
        return ResponseEntity.ok(productVariantsService.updateVariant(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteVariant(@PathVariable Long id) {
        return ResponseEntity.ok(productVariantsService.deleteVariant(id));
    }
}