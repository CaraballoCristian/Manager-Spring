package com.musa.project.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private CustomUserRepository customUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        CustomUser user = customUserRepository.findById(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username)
                );

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_USER")
                .build();
    }

}
