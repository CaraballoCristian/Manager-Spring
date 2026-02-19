package com.musa.project.product.dto;

import com.musa.project.product.utils.E_ProductSortBy;
import com.musa.project.product.models.E_Region;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetProductsQueryDTO {
    private E_Region region;

    private String category;

    private String term; // To search by name or description

    private E_ProductSortBy productSortBy;

}
