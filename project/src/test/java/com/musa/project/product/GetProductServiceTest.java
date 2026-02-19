package com.musa.project.product;

import com.musa.project.product.models.Product;
import com.musa.project.product.dto.GetProductsQueryDTO;
import com.musa.project.product.dto.ProductDTO;
import com.musa.project.product.repository.ProductRepository;
import com.musa.project.product.services.GetProductsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GetProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @Test
    void getProductsService_whenRepositoryReturnsEmptyList_returnsEmptyList() {

        // Arrange
        when(productRepository
                .findAll(any(Specification.class), any(Sort.class)))
                .thenReturn(List.of());

        // Act
        ResponseEntity<List<ProductDTO>> response = getProductsService
                .execute(new GetProductsQueryDTO(null, null, null, null));

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEmpty();

        // Verify
        verify(productRepository).findAll(any(Specification.class), any(Sort.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    void getProductsService_returnsProductListSuccessfully() {

        // Arrange
        Product product = new Product();
        product.setId("1");
        product.setName("Test product");

        when(productRepository.findAll(any(Specification.class), any(Sort.class)))
                .thenReturn(List.of(product));

        // Act
        ResponseEntity<List<ProductDTO>> response = getProductsService
                .execute(new GetProductsQueryDTO(null, null, null, null));

        // Assert
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(response.getBody())
                .hasSize(1);
        assertThat(response.getBody().get(0).getId())
                .isEqualTo("1");

        // Verify
        verify(productRepository).findAll(any(Specification.class), any(Sort.class));
        verifyNoMoreInteractions(productRepository);
    }

}
