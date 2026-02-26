package com.musa.project.user.validations;

import com.musa.project.exceptions.E_ErrorMessage;
import com.musa.project.exceptions.response.SimpleResponse;
import com.musa.project.exceptions.user.InvalidUserException;
import com.musa.project.security.auth.dto.RegisterRequestDTO;
import com.musa.project.user.model.User;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserValidator {

        public User execute(RegisterRequestDTO request) {

            // ---- FIELD VALIDATIONS ----
            if(isEmpty(request.getUsername())){
                throw new InvalidUserException(new SimpleResponse(
                        E_ErrorMessage.USER_NAME_CANNOT_BE_EMPTY.getMessage()),
                        request
                );
            }

            if (isEmpty(request.getPassword())) {
                throw new InvalidUserException(new SimpleResponse(
                        E_ErrorMessage.USER_PASSWORD_CANNOT_BE_EMPTY.getMessage()),
                        request
                );
            }

            if (isEmpty(request.getEmail())) {
                throw new InvalidUserException(new SimpleResponse(
                        E_ErrorMessage.USER_EMAIL_CANNOT_BE_EMPTY.getMessage()),
                        request
                );
            }

            return new User(request);
        };


        // ---- METHODS ----
        private static boolean isEmpty(String str) {
            return StringUtils.isEmpty(str);
        }


}

