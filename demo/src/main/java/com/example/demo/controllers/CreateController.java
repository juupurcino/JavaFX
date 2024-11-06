package com.example.demo.controllers;


import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginData;
import com.example.demo.impl.BcryptPasswordEncoderService;
import com.example.demo.impl.ExamplePassService;
import com.example.demo.model.User;
import com.example.demo.repositores.UserRepository;

@RestController
@RequestMapping("/user")
public class CreateController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamplePassService passService;
    
    @Autowired
    private BcryptPasswordEncoderService bcryptService;

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody LoginData data) {

        String username = data.username();
        String password = data.password();
        String email = data.email();

        if (password.length() < 4 || username.length() < 4 || email.length() < 4) {
            return ResponseEntity.ok("Todos os campos devem possuir ao menos 4 caracteres!");
        }

        String passwordValidationResponse = passService.verify(password); 
 
        if (!passwordValidationResponse.equals("ok")) {
            return ResponseEntity.ok(passwordValidationResponse);
        }

        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.ok("Nome de usuário já está em uso!");
        }

        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.ok("Email já está em uso!");
        }

        if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            return ResponseEntity.ok("O e-mail fornecido não tem um formato válido!");
        }

        String cripto = bcryptService.encode(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(cripto);
        user.setEmail(email);
        userRepository.save(user);

        return ResponseEntity.ok("Conta criada com sucesso!");
    }

}