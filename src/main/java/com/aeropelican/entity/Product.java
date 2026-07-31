package com.aeropelican.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

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
    @Column(name = "category_id")
    private Integer category_id;
    @Column(name = "description")
    private String description;
    @Column(name = "brand")
    private String brand;
    @Column(name = "is_active")
    private Boolean is_active = true;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;

}