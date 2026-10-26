package com.example.musicard.DTOs;

import com.example.musicard.entities.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    private String nome;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min = 6, max = 20)
    private String senha;

    private Role role; // só é usado quando o ADMIN cria/edita
}