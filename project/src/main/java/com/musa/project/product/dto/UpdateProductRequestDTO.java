package com.musa.project.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateProductRequestDTO {
    private String id;
    private ProductRequestDTO request;
}
