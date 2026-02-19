package com.musa.project.product.dto;

import com.musa.project.product.models.ProductSortBy;
import com.musa.project.product.models.Region;
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
