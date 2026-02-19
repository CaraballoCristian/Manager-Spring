package com.musa.project.security;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    LoginService loginService;

    NewUserService newUserService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return loginService.execute(request);
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<LoginResponse> createUser(@RequestBody LoginRequest request){
        return newUserService.execute(request);
    }

}
