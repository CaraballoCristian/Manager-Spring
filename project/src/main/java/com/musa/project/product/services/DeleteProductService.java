package com.musa.project.product.services;

import com.musa.project.utils.Command;
import com.musa.project.exceptions.product.ProductNotFoundException;
import com.musa.project.product.models.Product;
import com.musa.project.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class DeleteProductService implements Command<String, Void> {

    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<Void> execute(String id) {

        // ---- VALIDATION ----
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());

        // ---- DELETE ----
        productRepository.delete(product);

        // ---- LOGGER ----
        log.info("Product '{}' successfully Deleted with ID {}", product.getName(), id);

        return ResponseEntity.ok().build();
    }
}
