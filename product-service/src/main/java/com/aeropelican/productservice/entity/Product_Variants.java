package com.aeropelican.productservice.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
@Entity
@Data
@Table(name="product_variants")

public  class Product_Variants {
    @Id
    @Column(name = "variant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer variant_id;
    @Column(name = "product_id")
    private Integer product_id;
    @Column(name = "sku")
    private String sku;
    @Column(name = "color")
    private String color;
    @Column(name = "storage_capacity")
    private String storage_capacity;
    @Column(name = "price")
    private Double price;
    @Column(name = "is_active")
    private boolean is_active =true;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;
}