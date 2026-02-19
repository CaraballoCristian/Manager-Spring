package com.musa.project.product;

import com.musa.project.category.models.Category;
import com.musa.project.exceptions.ProductNotFoundException;
import com.musa.project.product.models.Product;
import com.musa.project.product.dto.ProductDTO;
import com.musa.project.product.repository.ProductRepository;
import com.musa.project.product.services.GetProductByIdService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetProductByIdServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductByIdService getProductByIdService;

    // ---- RETURNS ELEMENT ----
    @Test
    void getProductByIdService_whenProductExists_returnsProduct() {

        // Arrange
        String id = "1";
        Product product = new Product();
        product.setId(id);
        product.setCategory(new Category("Gaming"));

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<ProductDTO> response = getProductByIdService.execute(id);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // Here is when @EqualsAndHashCode in ProductDTO comes in handy
        assertThat(response.getBody()).isEqualTo(new ProductDTO(product)); // @EqualsAndHashCode in ProductDTO applies here

        // Verify
        verify(productRepository).findById(id);
        verifyNoMoreInteractions(productRepository);
    }

    // ---- RETURNS NOT FOUND ----
    @Test
    void getProductByIdService_whenProductDoesNotExist_throwsException() {

        // Arrange
        String id = "1";
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ProductNotFoundException exception =
                assertThrows(
                        ProductNotFoundException.class,
                        () -> getProductByIdService.execute(id)
                );

        // Assert
        assertThat(exception.getSimpleResponse().getMessage())
                .isEqualTo("Product not found");
        assertThat(exception.getHttpStatus())
                .isEqualTo(HttpStatus.NOT_FOUND);

        // Verify
        verify(productRepository).findById(id);
        verifyNoMoreInteractions(productRepository);

    }
}
