package com.aeropelican.productservice.controller;

import com.aeropelican.productservice.dto.response.CreateProductVariantsRequest;
import com.aeropelican.productservice.dto.response.UpdateProductVariants;
import com.aeropelican.productservice.entity.ProductVariants;
import com.aeropelican.productservice.service.ProductVariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/variants")
public class ProductVariantsController {

    @Autowired
    private ProductVariantsService productVariantsService;

    @PostMapping
    public ProductVariants createVariant(@RequestBody CreateProductVariantsRequest request) {
        return productVariantsService.saveVariant(request);
    }
}