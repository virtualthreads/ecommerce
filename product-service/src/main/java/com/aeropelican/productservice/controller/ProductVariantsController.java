package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.request.CreateProductVariantsRequest;
import com.aeropelican.productservice.dto.request.UpdateProductVariantsRequest;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.service.ProductVariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/variants")
public class ProductVariantsController {

    @Autowired
    private ProductVariantsService productVariantsService;

    @PostMapping
    public ResponseEntity<ProductVariants> saveVariant(@RequestBody CreateProductVariantsRequest request) {
        ProductVariants variant = productVariantsService.saveVariant(request);
        return new ResponseEntity<>(variant, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductVariants>> getAllVariants() {
        return ResponseEntity.ok(productVariantsService.getAllVariants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVariants> getVariantById(@PathVariable Long id) {
        return ResponseEntity.ok(productVariantsService.getVariantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductVariants> updateVariant(@PathVariable Long id, @RequestBody UpdateProductVariantsRequest request) {
        ProductVariants updatedVariant = productVariantsService.updateVariant(id, request);
        return ResponseEntity.ok(updatedVariant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVariant(@PathVariable Long id) {
        String response = productVariantsService.deleteVariant(id);
        return ResponseEntity.ok(response);
    }
}