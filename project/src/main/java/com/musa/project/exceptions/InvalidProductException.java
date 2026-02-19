package com.musa.project.Exceptions;

import com.musa.project.Product.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class InvalidProductException extends CustomBaseException {

    public InvalidProductException(SimpleResponse simpleResponse, ProductRequest productRequest) {
        super(HttpStatus.BAD_REQUEST,simpleResponse);
    }
}
