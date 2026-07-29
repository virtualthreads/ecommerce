package com.aeropelican.productservice.Exceptions;

public class ProductVariantNotFoundException extends RuntimeException {

    public ProductVariantNotFoundException(Long variantId) {
        super("Product variant not found with id: " + variantId);
    }
}