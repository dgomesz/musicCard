package com.example.musicard.entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.musicard.entities.enums.Role;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String nome;


    @NotBlank
    @Email
    @Column(unique = true, length = 150)
    private String email;

    @NotBlank
    @Length(min = 6, max = 20 ,message = "LIMITE DE CARACTERES EXCEDIDO" )
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;


}
