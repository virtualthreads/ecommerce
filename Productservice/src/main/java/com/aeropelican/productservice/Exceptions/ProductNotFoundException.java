package com.aeropelican.productservice.Exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Integer productId) {
        super("Product not found with id: " + productId);
    }
}