package com.musa.project.Product;

import com.musa.project.Category.CategoryRepository;
import com.musa.project.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CreateProductService implements Command<ProductRequest, ProductDTO> {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductValidator productValidator;

    @Override
    public ResponseEntity<ProductDTO> execute(ProductRequest request) {

        log.info("CreateProductService {}, {}", request, getClass().getSimpleName());

        // Validation
        Product validatedProduct = productValidator.execute(
                request,
                categoryRepository.findAll()
        );

        // Creation
        productRepository.save(validatedProduct);

        return ResponseEntity.ok(new ProductDTO(validatedProduct));
    }
}
