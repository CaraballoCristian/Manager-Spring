package com.musa.project.Exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class ProfanityFilterException extends CustomBaseException {

    public ProfanityFilterException() {
        super(
                HttpStatus.NOT_FOUND,
                new SimpleResponse(ErrorMessage.PROFANITY_FILTER_DOWN.getMessage())
        );
    }
}
