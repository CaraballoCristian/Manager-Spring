package com.musa.project.product.models;

import com.musa.project.category.models.Category;
import com.musa.project.product.dto.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @UuidGenerator
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String name;

    private String description;

    private String manufacturer;

    private Double price;

    // category
    @ManyToOne
    @JoinColumn(name="category_value", referencedColumnName="value")
    private Category category;

    // This annotation helps to save the enum in the DB with the content instead of the index
    @Enumerated(EnumType.STRING)
    private E_Region region;

    // With this annotations, any time we create a new entity or update one,
    // it will be instantly timestamped and saved to the database
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public Product(ProductRequestDTO request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.manufacturer = request.getManufacturer();
        this.price = request.getPrice();
        this.category = new Category(request.getCategory());
        this.region = E_Region.valueOf(request.getRegion());
    }
}
