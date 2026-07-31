package com.aeropelican.Exceptions;


public class Product_VariantsNotFound extends RuntimeException {
    public Product_VariantsNotFound(String message) {
        super(message);
    }
}