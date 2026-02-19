package com.musa.project.security.auth.service;

import com.musa.project.security.auth.dto.LoginRequestDTO;
import com.musa.project.security.auth.dto.LoginResponseDTO;
import com.musa.project.security.jwt.JWTUtil;
import com.musa.project.utils.Query;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoginService implements Query<LoginRequestDTO, LoginResponseDTO> {

    private AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<LoginResponseDTO> execute(LoginRequestDTO request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = JWTUtil.generateToken(request.getUsername());

        return ResponseEntity.ok(new LoginResponseDTO(jwt));
    }
}
