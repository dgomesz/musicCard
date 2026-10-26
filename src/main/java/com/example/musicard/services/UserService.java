package com.example.musicard.services;

import com.example.musicard.DTOs.UserRequest;
import com.example.musicard.DTOs.UserResponse;
import com.example.musicard.entities.User;
import com.example.musicard.entities.enums.Role;
import com.example.musicard.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service

public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    // Cadastro público: sempre USER
    public UserResponse register(UserRequest req) {
        return create(req, Role.USER);
    }

    // Criação pelo ADMIN: usa o papel enviado
    public UserResponse createByAdmin(UserRequest req) {
        return create(req, req.getRole() != null ? req.getRole() : Role.USER);
    }

    public List<UserResponse> findAll() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse findById(Long id) {
        return toResponse(getOrThrow(id));
    }

    public UserResponse me(String email) {
        return toResponse(repo.findByEmail(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")));
    }

    public UserResponse update(Long id, UserRequest req) {
        User u = getOrThrow(id);
        u.setNome(req.getNome());
        u.setEmail(req.getEmail());
        u.setSenha(encoder.encode(req.getSenha()));
        if (req.getRole() != null) u.setRole(req.getRole());
        return toResponse(repo.save(u));
    }

    public void delete(Long id) {
        repo.delete(getOrThrow(id));
    }

    public UserResponse create(UserRequest req, Role role) {
        if (repo.existsByEmail(req.getEmail()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
        User u = new User();
        u.setNome(req.getNome());
        u.setEmail(req.getEmail());
        u.setSenha(encoder.encode(req.getSenha()));
        u.setRole(role);
        return toResponse(repo.save(u));
    }

    public User getOrThrow(Long id) {
        return repo.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public UserResponse toResponse(User u) {
        UserResponse r = new UserResponse();
        r.setId(u.getId());
        r.setNome(u.getNome());
        r.setEmail(u.getEmail());
        r.setRole(u.getRole());
        return r;
    }
}