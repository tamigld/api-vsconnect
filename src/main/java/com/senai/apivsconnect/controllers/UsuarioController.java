package com.senai.apivsconnect.controllers;

import com.senai.apivsconnect.models.UsuarioModel;
import com.senai.apivsconnect.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value="/usuarios", produces = {"application/json"})
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioModel>>listarUsuarios(){
        //retorna response com a lista de usuários
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> exibirUsuario(@PathVariable(value = "id")UUID id){
        Optional<UsuarioModel> usuarioBuscado = usuarioRepository.findById(id);

        if (usuarioBuscado.isEmpty()){
            //retorna o user não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario nao encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioBuscado.get());

    }
}
