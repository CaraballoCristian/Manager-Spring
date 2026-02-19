package com.musa.project.exceptions;

import com.musa.project.product.dto.ProductRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class InvalidProductException extends CustomBaseException {

    public InvalidProductException(SimpleResponse simpleResponse, ProductRequestDTO productRequest) {
        super(HttpStatus.BAD_REQUEST,simpleResponse);
    }
}
