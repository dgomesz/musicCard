package com.example.musicard.controller;


import com.example.musicard.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastrar/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserDTO dto) {
        return ResponseEntity.ok(service.cadastrarUser(dto));

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> mostrarId(@PathVariable long id){
        return ResponseEntity.ok().body(service.mostrarId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUser(@PathVariable long id, @RequestBody UserDTO dto){
        return ResponseEntity.ok(service.editarUser(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> mostrarTodos() {
        return ResponseEntity.ok(service.mostrarTodosUser());
    }
}
