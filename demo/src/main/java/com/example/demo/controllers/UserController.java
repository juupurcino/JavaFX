package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Auth;
import com.example.demo.dto.LoginData;
import com.example.demo.dto.Token;
import com.example.demo.repositores.UserRepository;
import com.example.demo.services.JWTService;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService<Token> jwtService;

    @PostMapping
    public ResponseEntity<Auth> create(@RequestBody LoginData loginData) {

        if (loginData.login() == null || loginData.password() == null) {
            return new ResponseEntity<>(
                new Auth("login and password are expected.", null), HttpStatus.UNAUTHORIZED
            );
        }
        var users = repo.login(loginData.login());

        if (users.isEmpty()) {
            return new ResponseEntity<>(
                new Auth("The user do not exists.", null), HttpStatus.UNAUTHORIZED
            );
        }
        var user = users.get(0);

        if (!encoder.matches(loginData.password(), user.getPassword())) {
            return new ResponseEntity<>(
                new Auth("The password is incorrect.", null), HttpStatus.UNAUTHORIZED
            );
        }

        Token token = new Token();
        token.setId(user.getId());
        token.setUsername(user.getUsername());
        token.setEmail(user.getEmail());

        
        var jwt = jwtService.get(token);

        return new ResponseEntity<>(
            new Auth("Logado", String.valueOf(jwt)), HttpStatus.OK);
    }

}
