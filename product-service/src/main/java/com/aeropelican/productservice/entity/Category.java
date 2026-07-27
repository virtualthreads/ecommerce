package com.aeropelican.productservice.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer category_id;
    @Column(name = "category_name")
    private String category_name;
    @Column(name = "description")
    private String description;
    @Column(name = "parent_category_id")
    private Integer parent_category_id;
    @Column(name = "is_active")
    private boolean is_active = true;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "updated_at")
    private Timestamp updated_at;
}



