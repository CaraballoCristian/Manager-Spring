package com.musa.project.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class ProductNotFoundException extends CustomBaseException {

    public ProductNotFoundException() {

        super(
                HttpStatus.NOT_FOUND,
                new SimpleResponse(E_ErrorMessage.PRODUCT_NOT_FOUND.getMessage())
        );


    }
}
