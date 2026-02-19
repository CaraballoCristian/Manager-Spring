package com.musa.project.product;

import com.musa.project.category.models.Category;
import com.musa.project.exceptions.E_ErrorMessage;
import com.musa.project.exceptions.product.InvalidProductException;
import com.musa.project.integration.profanity.ProfanityValidator;
import com.musa.project.product.dto.ProductRequestDTO;
import com.musa.project.product.validations.ProductValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductValidatorTest {

    @Mock
    private ProfanityValidator profanityValidator;

    @InjectMocks
    private ProductValidator productValidator;

    // ---- NAME TESTING ----
    @Test
    void productValidatorTest_invalidName_throwsInvalidProductException() {
        // Name is Null
        ProductRequestDTO productRequestNull = getValidProductRequest();
        productRequestNull.setName(null);
        InvalidProductException exceptionNull = assertThrows(InvalidProductException.class,
                () -> productValidator.execute(productRequestNull, getCategories()));
        assertEquals(exceptionNull.getSimpleResponse().getMessage(), E_ErrorMessage.PRODUCT_NAME_CANNOT_BE_EMPTY.getMessage());

        // Name is Empty
        ProductRequestDTO productRequestEmpty = getValidProductRequest();
        productRequestEmpty.setName("");
        InvalidProductException exceptionEmpty = assertThrows(InvalidProductException.class,
                () -> productValidator.execute(productRequestEmpty, getCategories()));
        assertEquals(exceptionEmpty.getSimpleResponse().getMessage(), E_ErrorMessage.PRODUCT_NAME_CANNOT_BE_EMPTY.getMessage());
    }

    // ---- PRICE TESTING ----
    @Test
    void productValidatorTest_invalidPrice_throwsInvalidProductException() {
        // Price is null
        ProductRequestDTO productRequestNull = getValidProductRequest();
        productRequestNull.setPrice(null);
        InvalidProductException exceptionNull = assertThrows(InvalidProductException.class,
                () -> productValidator.execute(productRequestNull, getCategories()));
        assertEquals(exceptionNull.getSimpleResponse().getMessage(), E_ErrorMessage.PRODUCT_PRICE_CANNOT_BE_EMPTY.getMessage());

        // Price is Negative
        ProductRequestDTO productRequest = getValidProductRequest();
        productRequest.setPrice(-1.00);
        InvalidProductException exceptionNegative = assertThrows(InvalidProductException.class,
                () -> productValidator.execute(productRequest, getCategories()));
        assertEquals(exceptionNegative.getSimpleResponse().getMessage(), E_ErrorMessage.PRODUCT_PRICE_MUST_BE_GREATER_THAN_ZERO.getMessage());
    }

    // ---- CATEGORY IS NOT AVAILABLE ----
    @Test
    void productValidatorTest_invalidCategory_throwsInvalidProductException() {
        // Valid Category But Category List is Empty
        ProductRequestDTO productRequestEmpty = getValidProductRequest();
        productRequestEmpty.setCategory("TECH");
        InvalidProductException exceptionEmpty = assertThrows(InvalidProductException.class,
                () -> productValidator.execute(productRequestEmpty, Collections.emptyList()));
        assertEquals(exceptionEmpty.getSimpleResponse().getMessage(), E_ErrorMessage.PRODUCT_CATEGORY_MUST_BE_AN_EXISTING_CATEGORY.getMessage());

        // Pushing invalid Categorie
        ProductRequestDTO productRequestInvalid = getValidProductRequest();
        productRequestInvalid.setCategory("invalidCategory");
        InvalidProductException exceptionInvalid = assertThrows(InvalidProductException.class,
                () -> productValidator.execute(productRequestInvalid, getCategories()));
        assertEquals(exceptionInvalid.getSimpleResponse().getMessage(), E_ErrorMessage.PRODUCT_CATEGORY_MUST_BE_AN_EXISTING_CATEGORY.getMessage());
    }

    // ---- REGION IS NOT AVAILABLE ----
    @Test
    void productValidatorTest_invalidRegion_throwsInvalidProductException() {
        ProductRequestDTO productRequest = getValidProductRequest();
        productRequest.setRegion("invalidRegion");
        InvalidProductException exception = assertThrows(InvalidProductException.class,
                () -> productValidator.execute(productRequest, getCategories()));
        assertEquals(exception.getSimpleResponse().getMessage(), E_ErrorMessage.PRODUCT_REGION_MUST_BE_AN_EXISTING_REGION.getMessage());
    }

    // ---- PROFANITY TEST ----
    @Test
    void productValidatorTest_productHasProfanity_throwsInvalidProductException() {
        ProductRequestDTO productRequest = getValidProductRequest();

        when(profanityValidator.hasProfanity(any())).thenReturn(true);

        InvalidProductException exception = assertThrows(InvalidProductException.class,
                () -> productValidator.execute(productRequest, getCategories()));
        assertEquals(exception.getSimpleResponse().getMessage(), E_ErrorMessage.PRODUCT_HAS_PROFANITY.getMessage());
    }

    // HELPERS METHODS
    private ProductRequestDTO getValidProductRequest() {
        return new ProductRequestDTO(
                "testName",
                "testDescription",
                "testManufacturer",
                "TECH",
                "USA",
                29.99
        );
    }

    private List<Category> getCategories() {
        return Arrays.asList(new Category("TECH"), new Category("FOOD"));
    }
}
