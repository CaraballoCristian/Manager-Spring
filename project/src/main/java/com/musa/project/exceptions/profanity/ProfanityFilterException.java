package com.musa.project.exceptions.profanity;

import com.musa.project.exceptions.E_ErrorMessage;
import com.musa.project.exceptions.response.SimpleResponse;
import com.musa.project.exceptions.base.CustomBaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class ProfanityFilterException extends CustomBaseException {

    public ProfanityFilterException() {
        super(
                HttpStatus.NOT_FOUND,
                new SimpleResponse(E_ErrorMessage.PROFANITY_FILTER_DOWN.getMessage())
        );
    }
}
