package com.musa.project.product.services;

import com.musa.project.exceptions.ProductNotFoundException;
import com.musa.project.utils.Query;
import com.musa.project.product.models.Product;
import com.musa.project.product.dto.ProductDTO;
import com.musa.project.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class GetProductByIdService implements Query<String, ProductDTO> {

    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<ProductDTO> execute(String id) {

        // ---- VALIDATIONS ----
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());

        // ---- LOGGER ----
        log.info("Product '{}' successfully found with ID {}", product.getName(), id);

        return ResponseEntity.ok(new ProductDTO(product));
    }
}
