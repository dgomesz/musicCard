package com.example.musicard.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MusicaResponse {
    private Long id;
    private String nameSong;
    private String descriMsc;
    private LocalDate dtLaunch;
    private String category;
    private String nameArtist;
}