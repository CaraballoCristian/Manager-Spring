package com.musa.project.product.services;

import com.musa.project.category.repository.CategoryRepository;
import com.musa.project.utils.Command;
import com.musa.project.product.models.Product;
import com.musa.project.product.dto.ProductDTO;
import com.musa.project.product.dto.ProductRequestDTO;
import com.musa.project.product.repository.ProductRepository;
import com.musa.project.product.validations.ProductValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CreateProductService implements Command<ProductRequestDTO, ProductDTO> {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductValidator productValidator;

    @Override
    public ResponseEntity<ProductDTO> execute(ProductRequestDTO request) {

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
