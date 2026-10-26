package com.example.musicard.services;

import com.example.musicard.DTOs.MusicaRequest;
import com.example.musicard.DTOs.MusicaResponse;
import com.example.musicard.entities.Musica;
import com.example.musicard.repositories.MusicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



@Service
@RequiredArgsConstructor
public class MusicaService {
    private final MusicaRepository repo;
    private final ArtistaService artistaService;

    public MusicaResponse create(MusicaRequest req) {
        return salvar(new Musica(), req);
    }

    public List<MusicaResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public MusicaResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public MusicaResponse update(Long id, MusicaRequest req) {
        return salvar(getOrThrow(id), req);
    }

    public void delete(Long id) {
        repo.delete(getOrThrow(id).getArtista());
    }

    public Musica getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Música não encontrada"));
    }

    public MusicaResponse salvar(Musica m, MusicaRequest req) {
        m.setNameSong(req.getNameSong());
        m.setDescriMsc(req.getDescriMsc());
        m.setDtLaunch(req.getDtLaunch());
        m.setCategory(req.getCategory());
        m.setArtista(artistaService.getOrThrow(req.getArtistaId()));
        return toResponse(musicaRepository.save(m));
    }

    public MusicaResponse toResponse(Musica m) {
        MusicaResponse r = new MusicaResponse();
        r.setId(m.getId());
        r.setNameSong(m.getNameSong());
        r.setDescriMsc(m.getDescriMsc());
        r.setDtLaunch(m.getDtLaunch());
        r.setCategory(m.getCategory());
        r.setNameArtist(m.getArtista().getNameArtist());
        return r;
    }
}