package com.example.musicard.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArtistaResponse {
    private Long id;
    private String nameArtist;
    private String category;
    private LocalDate dtDateOfBirth;
}