package com.example.musicard.services;

import com.example.musicard.DTOs.ArtistaRequest;
import com.example.musicard.DTOs.ArtistaResponse;
import com.example.musicard.entities.Artista;
import com.example.musicard.repositories.ArtistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistaService {
    private final ArtistaRepository repo;

    public ArtistaResponse create(ArtistaRequest req) {
        return salvar(new Artista(), req);
    }

    public List<ArtistaResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public ArtistaResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public ArtistaResponse update(Long id, ArtistaRequest req) {
        return salvar(getOrThrow(id), req);
    }

    public void delete(Long id) {
        try {
            repo.delete(getOrThrow(id));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Artista possui músicas cadastradas");
        }
    }

    public Artista getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista não encontrado"));
    }

    public ArtistaResponse salvar(Artista a, ArtistaRequest req) {
        a.setNameArtist(req.getNameArtist());
        a.setCategory(req.getCategory());
        a.setDtDateOfBirth(req.getDtDateOfBirth());
        return toResponse(repo.save(a));
    }

    public ArtistaResponse toResponse(Artista a) {
        ArtistaResponse r = new ArtistaResponse();
        r.setId(a.getId());
        r.setNameArtist(a.getNameArtist());
        r.setCategory(a.getCategory());
        r.setDtDateOfBirth(a.getDtDateOfBirth());
        return r;
    }
}