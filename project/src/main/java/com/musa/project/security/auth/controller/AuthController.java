package com.musa.project.security.auth.controller;

import com.musa.project.security.auth.dto.LoginRequestDTO;
import com.musa.project.security.auth.dto.LoginResponseDTO;
import com.musa.project.security.auth.service.LoginService;
import com.musa.project.security.auth.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    LoginService loginService;

    RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        return loginService.execute(request);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> createUser(@RequestBody LoginRequestDTO request){
        return registerService.execute(request);
    }

}
