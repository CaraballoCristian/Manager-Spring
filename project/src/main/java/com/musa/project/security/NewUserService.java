package com.musa.project.security;

import com.musa.project.utils.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class NewUserService implements Command<LoginRequest, LoginResponse> {

    private final CustomUserRepository customUserRepository;

    private final BCryptPasswordEncoder encoder;

    private final LoginService loginService;

    @Override
    public ResponseEntity<LoginResponse> execute(LoginRequest request) {
        CustomUser user = new CustomUser();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        customUserRepository.save(user);


        return loginService.execute(request);
    }
}
