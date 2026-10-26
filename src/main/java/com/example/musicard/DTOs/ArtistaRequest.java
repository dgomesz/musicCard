package com.example.musicard.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ArtistaRequest {
    @NotBlank
    private String nameArtist;
    private String category;
    private LocalDate dtDateOfBirth;
}

