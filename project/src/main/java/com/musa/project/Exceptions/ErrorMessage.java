package com.musa.project.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    PRODUCT_NOT_FOUND("Product not found"),
    PRODUCT_NAME_CANNOT_BE_EMPTY("Product name cannot be empty"),
    PRODUCT_PRICE_CANNOT_BE_EMPTY("Product price cannot be empty"),
    PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO("Product price must be greater than zero"),
    PRODUCT_CATEGORY_MUST_BE_AN_EXISTING_CATEGORY("Category not found"),
    PRODUCT_REGION_MUST_BE_AN_EXISTING_REGION("Region not found"),
    PRODUCT_HAS_PROFANITY("Product cannot be saved due to explicit keywords"),
    PROFANITY_FILTER_DOWN("Profanity filter external service is down");

    private final String message;
}
