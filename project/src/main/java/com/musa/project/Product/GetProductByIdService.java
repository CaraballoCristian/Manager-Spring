package com.musa.project.Product;

import com.musa.project.Exceptions.ProductNotFoundException;
import com.musa.project.Query;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
