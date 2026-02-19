package com.musa.project.product;

import com.musa.project.category.Category;
import com.musa.project.product.domain.Product;
import lombok.*;

@Data
// @EqualsAndHashCode Overrides the equals() method
// & compares the content of objects instead of memory address
// which is necessary for correct testing
@EqualsAndHashCode
public class ProductDTO {

    private String id;

    private String name;

    private String description;

    private String manufacturer;

    private Double price;

    private Category category;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.manufacturer = product.getManufacturer();
        this.price = product.getPrice();
        this.category = product.getCategory();
    }
}
