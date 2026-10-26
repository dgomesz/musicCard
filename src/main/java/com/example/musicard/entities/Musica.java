package com.example.musicard.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Musica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nameSong;

    @NotBlank
    private String descriMsc;

    private LocalDate dtLaunch;
    private String category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artista_id")
    private Artista artista;
}