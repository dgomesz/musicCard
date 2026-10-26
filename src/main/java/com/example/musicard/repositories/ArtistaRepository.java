package com.example.musicard.repositories;

import com.example.musicard.entities.Admin;
import com.example.musicard.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}
