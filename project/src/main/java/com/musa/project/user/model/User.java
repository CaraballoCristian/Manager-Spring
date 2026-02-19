package com.musa.project.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    private String username;
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

}
