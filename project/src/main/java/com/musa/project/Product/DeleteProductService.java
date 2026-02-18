package com.musa.project.Product;

import com.musa.project.Command;
import com.musa.project.Exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
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
