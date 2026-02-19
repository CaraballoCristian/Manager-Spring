package com.musa.project.product.utils;

import org.springframework.data.domain.Sort;

import java.util.Arrays;

public enum ProductSortBy {
    nameAsc("name", Sort.Direction.ASC),
    nameDesc("name", Sort.Direction.DESC),
    priceAsc("price", Sort.Direction.ASC),
    priceDesc("price", Sort.Direction.DESC);

    private final String field;
    private final Sort.Direction direction;

    ProductSortBy(String field, Sort.Direction direction) {
        this.field = field;
        this.direction = direction;
    }

    public Sort toSort() {
        return Sort.by(direction, field);
    }

    // Searches for the parameter value across all enum elements and return it if exists, else return null
    public static ProductSortBy fromValue(String value) {
        if(value == null) return null;

        return Arrays.stream(ProductSortBy.values())
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
