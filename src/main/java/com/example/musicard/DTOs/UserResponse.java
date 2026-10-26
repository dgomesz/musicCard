package com.example.musicard.DTOs;

import com.example.musicard.entities.enums.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String nome;
    private String email;
    private Role role;
}