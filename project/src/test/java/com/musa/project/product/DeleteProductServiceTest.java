package com.musa.project.Product;

import com.musa.project.Exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@SpringBootTest(classes = ProjectApplication.class)
@ExtendWith(MockitoExtension.class)
public class DeleteProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    // ---- DELETE SUCCESSFULLY ----
    @Test
    void deleteProductService_whenProductExists_deletesAndReturnsOk() {
        // Arrange
        String id = "1";
        Product product = new Product();
        product.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<?> response = deleteProductService.execute(id);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Verify
        verify(productRepository).delete(product);
    }


    // ---- RETURNS PRODUCT NOT FOUND ----
    @Test
    void deleteProductService_whenProductDoesNotExist_throwsProductNotFound() {
        // Arrange
        String id = "1";

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        ProductNotFoundException exception =
                assertThrows(
                        ProductNotFoundException.class,
                        () -> deleteProductService.execute(id)
                );

        // Assert
        assertThat(exception.getSimpleResponse().getMessage())
                .isEqualTo("Product not found");
        assertThat(exception.getHttpStatus())
                .isEqualTo(HttpStatus.NOT_FOUND);

    }
}
