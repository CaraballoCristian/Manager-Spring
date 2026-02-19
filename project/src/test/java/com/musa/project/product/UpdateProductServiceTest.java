package com.musa.project.product;

import com.musa.project.category.repository.CategoryRepository;
import com.musa.project.exceptions.product.ProductNotFoundException;
import com.musa.project.product.models.Product;
import com.musa.project.product.dto.ProductDTO;
import com.musa.project.product.dto.ProductRequestDTO;
import com.musa.project.product.dto.UpdateProductRequestDTO;
import com.musa.project.product.repository.ProductRepository;
import com.musa.project.product.services.UpdateProductService;
import com.musa.project.product.validations.ProductValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductValidator productValidator;

    @InjectMocks
    private UpdateProductService updateProductService;

    @Mock
    private UpdateProductRequestDTO updateRequest;

    @Mock
    private ProductRequestDTO productRequest;

    // --------------------------
    // PRODUCT NOT FOUND
    // --------------------------
    @Test
    void execute_whenProductDoesNotExist_shouldThrow() {

        when(updateRequest.getId()).thenReturn("ID1");
        when(productRepository.findById("ID1"))
                .thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class,
                () -> updateProductService.execute(updateRequest));

        verify(productRepository).findById("ID1");
        verifyNoInteractions(productValidator);
    }

    // --------------------------
    // HAPPY PATH
    // --------------------------
    @Test
    void execute_whenValid_shouldUpdateAndReturnProductDTO() {

        // --- Request ---
        when(updateRequest.getId()).thenReturn("ID2");
        when(updateRequest.getRequest()).thenReturn(productRequest);

        // --- Existing product in DB ---
        Product existing = new Product();
        existing.setId("ID2");
        existing.setCreatedAt(LocalDateTime.now());

        when(productRepository.findById("ID2"))
                .thenReturn(Optional.of(existing));

        // --- Categories (simple list) ---
        when(categoryRepository.findAll())
                .thenReturn(Collections.emptyList());

        // --- Validator returns a valid Product ---
        Product validated = new Product();
        validated.setName("New Name");
        validated.setDescription("New Desc");

        when(productValidator.execute(productRequest, Collections.emptyList()))
                .thenReturn(validated);

        // --- Ejecutar ---
        ResponseEntity<ProductDTO> response = updateProductService.execute(updateRequest);

        // --- Assertions ---
        assertNotNull(response.getBody());
        ProductDTO dto = response.getBody();

        assertEquals("ID2", dto.getId());
        assertEquals("New Name", dto.getName());
        assertEquals("New Desc", dto.getDescription());

        // Save must be called with the validated product
        verify(productRepository).save(validated);
    }
}
