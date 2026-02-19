package com.musa.project.product;

import com.musa.project.category.repository.CategoryRepository;
import com.musa.project.exceptions.InvalidProductException;
import com.musa.project.exceptions.SimpleResponse;
import com.musa.project.product.models.Product;
import com.musa.project.product.dto.ProductDTO;
import com.musa.project.product.dto.ProductRequestDTO;
import com.musa.project.product.repository.ProductRepository;
import com.musa.project.product.services.CreateProductService;
import com.musa.project.product.validations.ProductValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ProductValidator productValidator;

    @InjectMocks
    CreateProductService createProductService;

    ProductRequestDTO request = new ProductRequestDTO(
            "name",
            "desc",
            "manu",
            "TECH",
            "USA",
            20.0
    );

    // -------------------------
    // SUCCESS
    // -------------------------
    @Test
    void execute_shouldCreateProductAndReturnDTO() {
        // Arrange
        Product validated = new Product();
        validated.setName("name");

        when(categoryRepository.findAll()).thenReturn(List.of());
        when(productValidator.execute(request, List.of())).thenReturn(validated);

        // Act
        ResponseEntity<ProductDTO> response = createProductService.execute(request);

        // Assert
        assertEquals("name", response.getBody().getName());
        assertEquals(200, response.getStatusCode().value());

        verify(productValidator).execute(request, List.of());
        verify(productRepository).save(validated);
    }

    // -------------------------
    // VALIDATOR THROWS
    // -------------------------
    @Test
    void execute_whenValidatorThrows_shouldPropagateException() {
        SimpleResponse simple = new SimpleResponse("invalid");
        InvalidProductException invalid = new InvalidProductException(simple, request);

        when(categoryRepository.findAll()).thenReturn(List.of());
        when(productValidator.execute(request, List.of())).thenThrow(invalid);

        // Assert + Act
        assertThrows(InvalidProductException.class, () -> createProductService.execute(request));

        verify(productRepository, never()).save(any());
    }
}
