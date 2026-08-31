package com.example.musicard.DTOs;


import com.example.musicard.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    @Column(unique = true, length = 150)
    private String email;

    @NotBlank
    @Length(min = 6, max = 20 ,message = "LIMITE DE CARACTERES EXCEDIDO")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserDTO(String nome, String email, String senha, Role role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }
}
