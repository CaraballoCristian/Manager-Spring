package com.musa.project.security.auth.service;

import com.musa.project.security.auth.dto.LoginRequestDTO;
import com.musa.project.security.auth.dto.LoginResponseDTO;
import com.musa.project.security.auth.dto.RegisterRequestDTO;
import com.musa.project.user.model.User;
import com.musa.project.user.repository.UserRepository;
import com.musa.project.user.validations.UserValidator;
import com.musa.project.utils.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterService implements Command<RegisterRequestDTO, LoginResponseDTO> {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final LoginService loginService;

    private final UserValidator userValidator;

    @Override
    public ResponseEntity<LoginResponseDTO> execute( RegisterRequestDTO request) {

        log.info("RegisterService {}, {}", request, getClass().getSimpleName());

        //Validations
        User validatedUser = userValidator.execute(request);
        validatedUser.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(validatedUser);

        return loginService.execute(
                new LoginRequestDTO(
                        request.getUsername(),
                        request.getPassword()
                )
        );
    }
}
