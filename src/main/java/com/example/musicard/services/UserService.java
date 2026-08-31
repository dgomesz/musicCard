package com.example.musicard.services;


import com.example.musicard.DTOs.UserDTO;
import com.example.musicard.entities.User;
import com.example.musicard.entities.enums.Role;
import com.example.musicard.repositories.UserRepositories;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepositories userRepositories;
    public UserService(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }
public String cadastrarUser(UserDTO t){
        User user = new User();
        user.setEmail(t.getEmail());
        user.setNome(t.getNome());
        user.setSenha(t.getSenha());
        user.setRole(Role.USER);
        userRepositories.save(user);
    return "Usuario cadastrado com sucesso!, bem vindo ao Musicard!";


}

}
