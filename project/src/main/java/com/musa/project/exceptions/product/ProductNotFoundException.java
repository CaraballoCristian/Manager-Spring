package com.musa.project.exceptions.product;

import com.musa.project.exceptions.E_ErrorMessage;
import com.musa.project.exceptions.response.SimpleResponse;
import com.musa.project.exceptions.base.CustomBaseException;
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
