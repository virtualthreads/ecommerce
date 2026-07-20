package com.aeropelican.productservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "products")
@Entity
@Data
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock_quantity")
    private Integer quantity;
}