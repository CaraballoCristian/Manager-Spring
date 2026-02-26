package com.musa.project.exceptions.user;

import com.musa.project.exceptions.base.CustomBaseException;
import com.musa.project.exceptions.response.SimpleResponse;
import com.musa.project.security.auth.dto.RegisterRequestDTO;
import org.springframework.http.HttpStatus;

public class InvalidUserException extends CustomBaseException {
    public InvalidUserException(SimpleResponse simpleResponse, RegisterRequestDTO userRequest) {
        super(HttpStatus.BAD_REQUEST,simpleResponse);
    }


}
