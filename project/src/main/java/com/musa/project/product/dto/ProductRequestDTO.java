package com.musa.project.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDTO {
    private String name;
    private String description;
    private String manufacturer;
    private String category;
    private String region;
    private Double price;
}
