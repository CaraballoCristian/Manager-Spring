package com.musa.project.Product;

import com.musa.project.Category.Category;
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

    //category
    @ManyToOne
    @JoinColumn(name="category_value", referencedColumnName="value")
    private Category category;

    // This annotation helps to save the enum in the DB with the content instead of the index
    @Enumerated(EnumType.STRING)
    private Region region;

    // With this annotations, any time we create a new entity or update one,
    // it will be instantly timestamped and saved to the database
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    // Constructor (mepa que esta al pedo si uso lombock, testear)
    public Product(ProductRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.manufacturer = request.getManufacturer();
        this.price = request.getPrice();
        this.category = new Category(request.getCategory());
        this.region = Region.valueOf(request.getRegion());
    }
}
