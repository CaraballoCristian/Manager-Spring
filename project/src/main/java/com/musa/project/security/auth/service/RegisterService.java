package com.musa.project.security.auth.service;

import com.musa.project.security.auth.dto.LoginRequestDTO;
import com.musa.project.security.auth.dto.LoginResponseDTO;
import com.musa.project.user.model.User;
import com.musa.project.user.repository.UserRepository;
import com.musa.project.utils.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterService implements Command<LoginRequestDTO, LoginResponseDTO> {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    private final LoginService loginService;

    @Override
    public ResponseEntity<LoginResponseDTO> execute(LoginRequestDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepository.save(user);

        return loginService.execute(request);
    }
}
