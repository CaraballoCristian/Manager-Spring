package com.musa.project.exceptions.base;

import com.musa.project.exceptions.response.SimpleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CustomBaseException extends RuntimeException {

    private HttpStatus httpStatus;

    private SimpleResponse simpleResponse;
}
