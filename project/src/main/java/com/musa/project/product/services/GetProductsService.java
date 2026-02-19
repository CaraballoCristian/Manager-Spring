package com.musa.project.product.services;

import com.musa.project.utils.Query;
import com.musa.project.product.models.Product;
import com.musa.project.product.utils.E_ProductSortBy;
import com.musa.project.product.dto.GetProductsQueryDTO;
import com.musa.project.product.dto.ProductDTO;
import com.musa.project.product.repository.ProductRepository;
import com.musa.project.product.utils.ProductSpecifications;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
public class GetProductsService implements Query<GetProductsQueryDTO, List<ProductDTO>> {

    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(GetProductsQueryDTO query) {

        // ---- LOGGER ----
        log.info("GetProductsService query : {}", query);

        // ---- SPECIFICATIONS ----
        Specification<Product> spec = Specification
                .where(ProductSpecifications.termContains(query.getTerm()))
                .and(ProductSpecifications.hasCategory(query.getCategory()))
                .and(ProductSpecifications.hasRegion(query.getRegion()));

        // ---- SORTING ----
        Sort sort = Optional.ofNullable(query.getProductSortBy())
                .map(E_ProductSortBy::toSort)
                .orElse(Sort.unsorted());

        // ---- QUERY ----
        List<Product> products = productRepository.findAll(spec, sort);

        return ResponseEntity.ok(products.stream().map(ProductDTO::new).toList());
    }
}
