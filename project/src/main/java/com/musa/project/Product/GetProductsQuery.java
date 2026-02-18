package com.musa.project.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductsQuery {
    private Region region;

    private String category;

    private String term; // To search by name or description

    private ProductSortBy productSortBy;

}
