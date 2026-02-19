package com.musa.project.product.utils;

import com.musa.project.product.models.Product;
import com.musa.project.product.models.E_Region;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    // Name or Description Contains
    public static Specification<Product> termContains(String term) {
        return (root, query, cb) -> {
            if (term == null || term.isBlank()) return null;

            String like = "%" + term.toLowerCase() + "%";

            return cb.or(
                    cb.like(cb.lower(root.get("name")), like),
                    cb.like(cb.lower(root.get("description")), like)
            );
        };
    }

    // Region equals
    public static Specification<Product> hasRegion(E_Region region) {
        return (root, query, cb) ->
                cb.equal(root.get("region"), region);
    }

    // Category equals
    public static Specification<Product> hasCategory(String category) {
        return (root, query, cb) -> {
            if (category == null || category.isBlank()) return null;

            return cb.equal(cb.lower(root.get("category").get("value")), category.toLowerCase());
        };
    }
}
