package com.musa.project.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CustomBaseException extends RuntimeException {

    private HttpStatus httpStatus;

    private SimpleResponse simpleResponse;
}
