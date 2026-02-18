package com.musa.project.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private String manufacturer;
    private String category;
    private String region;
    private Double price;
}
