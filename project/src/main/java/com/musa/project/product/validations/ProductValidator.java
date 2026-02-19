package com.musa.project.product;

import com.musa.project.category.Category;
import com.musa.project.exceptions.ErrorMessage;
import com.musa.project.exceptions.InvalidProductException;
import com.musa.project.exceptions.ProfanityValidator;
import com.musa.project.exceptions.SimpleResponse;
import com.musa.project.product.domain.Product;
import com.musa.project.product.domain.Region;
import com.musa.project.product.dto.ProductRequest;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class ProductValidator {

    public final ProfanityValidator profanityValidator;

    public Product execute(ProductRequest request, List<Category> categoriesAllowed) {

        // ---- FIELD VALIDATIONS ----
        if(nameIsEmpty(request.getName())){
            throw new InvalidProductException(new SimpleResponse(
                    ErrorMessage.PRODUCT_NAME_CANNOT_BE_EMPTY.getMessage()),
                    request
            );
        }

        if (priceIsEmpty(request.getPrice())) {
            throw new InvalidProductException(new SimpleResponse(
                    ErrorMessage.PRODUCT_PRICE_CANNOT_BE_EMPTY.getMessage()),
                    request
            );
        }

        if(priceIsNegative(request.getPrice())){
            throw new InvalidProductException(new SimpleResponse(
                    ErrorMessage.PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO.getMessage()),
                    request
            );
        }

        if(categoryIsNotAllowed(request.getCategory(), categoriesAllowed)){
            throw new InvalidProductException(new SimpleResponse(
                    ErrorMessage.PRODUCT_CATEGORY_MUST_BE_AN_EXISTING_CATEGORY.getMessage()),
                    request
            );
        }

        if(regionNotAvailable(request.getRegion())){
            throw new InvalidProductException(new SimpleResponse(
                    ErrorMessage.PRODUCT_REGION_MUST_BE_AN_EXISTING_REGION.getMessage()),
                    request
            );
        }

        // ---- PROFANITY VALIDATIONS ----
        if(profanityValidator.hasProfanity(request)){
            throw new InvalidProductException(new SimpleResponse(
                    ErrorMessage.PRODUCT_HAS_PROFANITY.getMessage()),
                    request
            );
        }

        return new Product(request);
    };


    // ---- METHODS ----
    private static boolean nameIsEmpty(String name) {
        return StringUtils.isEmpty(name);
    }

    private static boolean priceIsEmpty(Double price) {
        return price == null;
    }

    private static boolean priceIsNegative(Double price) {
         return price <= 0.00;
    }

    private static boolean categoryIsNotAllowed(String category, List<Category> categoriesAllowed) {
        return !categoriesAllowed.contains(new Category(category));
    }

    private static boolean regionNotAvailable(String newRegion) {
        return Arrays.stream(Region.values())
                .noneMatch(region
                        -> region.name()
                        .equals(newRegion)
                );
    }

}
