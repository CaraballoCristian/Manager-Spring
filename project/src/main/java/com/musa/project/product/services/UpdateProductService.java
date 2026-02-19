package com.musa.project.product.services;

import com.musa.project.category.repository.CategoryRepository;
import com.musa.project.utils.Command;
import com.musa.project.exceptions.ProductNotFoundException;
import com.musa.project.product.models.Product;
import com.musa.project.product.dto.ProductDTO;
import com.musa.project.product.dto.UpdateProductRequestDTO;
import com.musa.project.product.repository.ProductRepository;
import com.musa.project.product.validations.ProductValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UpdateProductService implements Command<UpdateProductRequestDTO, ProductDTO> {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductValidator productValidator;

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductRequestDTO request) {

        // ---- LOGGER ----
        log.info("UpdateProductService {}, {}", request, getClass().getSimpleName());

        // ---- CHECK IF EXISTS ----
        Optional<Product> productOptional = productRepository.findById(request.getId());

        if (productOptional.isEmpty()) throw new ProductNotFoundException();

        // ---- VALIDATIONS ----
        Product validatedProduct = productValidator.execute(
                request.getRequest(),
                categoryRepository.findAll()
        );

        // ---- SET DATA ----
        validatedProduct.setId(productOptional.get().getId());
        validatedProduct.setCreatedAt(productOptional.get().getCreatedAt());

        // ---- UPDATE ----
        productRepository.save(validatedProduct);

        return ResponseEntity.ok(new ProductDTO(validatedProduct));
    }


}

