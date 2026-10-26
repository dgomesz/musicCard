package com.example.musicard.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MusicaRequest {
    @NotBlank
    private String nameSong;
    @NotBlank
    private String descriMsc;
    private LocalDate dtLaunch;
    private String category;
    @NotNull
    private Long artistaId;
}