package com.musa.project.exceptions.product;

import com.musa.project.exceptions.response.SimpleResponse;
import com.musa.project.exceptions.base.CustomBaseException;
import com.musa.project.product.dto.ProductRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class InvalidProductException extends CustomBaseException {

    public InvalidProductException(SimpleResponse simpleResponse, ProductRequestDTO productRequest) {
        super(HttpStatus.BAD_REQUEST,simpleResponse);
    }
}
