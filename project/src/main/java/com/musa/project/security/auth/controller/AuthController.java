package com.musa.project.security.auth.controller;

import com.musa.project.security.auth.dto.LoginRequestDTO;
import com.musa.project.security.auth.dto.LoginResponseDTO;
import com.musa.project.security.auth.dto.RegisterRequestDTO;
import com.musa.project.security.auth.service.LoginService;
import com.musa.project.security.auth.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final LoginService loginService;

    private final RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        return loginService.execute(request);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> createUser(@RequestBody RegisterRequestDTO request){
        return registerService.execute(request);
    }

}
