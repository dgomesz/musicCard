package com.example.musicard.entities;

import com.example.musicard.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank @Email
    @Column(unique = true, length = 150)
    private String email;

    @NotBlank
    @Column(length = 100)
    private String senha; // hash BCrypt

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
