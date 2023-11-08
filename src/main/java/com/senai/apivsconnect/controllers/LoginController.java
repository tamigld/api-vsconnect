package com.senai.apivsconnect.controllers;


import com.senai.apivsconnect.dtos.LoginDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.senha());
        // criação do objeto de username e password
        var auth = authenticationManager.authenticate(usernamePassword);
        // faz a autenticação do usernamePassword
        return ResponseEntity.status(HttpStatus.OK).body("Logadoooooo!");
    }
}
