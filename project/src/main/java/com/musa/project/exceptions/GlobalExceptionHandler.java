package com.musa.project.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
// Falta implementar esto (Buena practica para gestionar exceptions de forma generica), need to refactor
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SimpleResponse> handleException(CustomBaseException exception) {

        // ---- LOGGER ----
        log.error(
                "{} {}",
                exception.getHttpStatus().value(),
                exception.getSimpleResponse().getMessage()
        );

        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(exception.getSimpleResponse());
    }
}
