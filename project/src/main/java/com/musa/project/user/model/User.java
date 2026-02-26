package com.musa.project.user.model;

import com.musa.project.security.auth.dto.RegisterRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="users")
@NoArgsConstructor
public class User {
    @Id
    private String username;
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    // Comes from validator
    public User(RegisterRequestDTO request) {
        this.username = request.getUsername();
        this.password = request.getPassword();
        this.email = request.getEmail();
    }

}
