package com.musa.project.Product;

import com.musa.project.Category.CategoryRepository;
import com.musa.project.Exceptions.InvalidProductException;
import com.musa.project.Exceptions.SimpleResponse;
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

    ProductRequest request = new ProductRequest(
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
