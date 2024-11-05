package com.example.demo.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.services.PassService;

import java.util.regex.Pattern;

@Service
public class ExamplePassService implements PassService{
    
    @Override
    public ResponseEntity<String> verify(String password) {
    if (password.length() < 8) {
        return ResponseEntity.badRequest().body("A senha deve possuir ao menos 8 caracteres!");
    }

    if (!Pattern.compile("[a-z]").matcher(password).find()) {
        return ResponseEntity.badRequest().body("A senha deve conter pelo menos uma letra minúscula!");
    }

    if (!Pattern.compile("[A-Z]").matcher(password).find()) {
        return ResponseEntity.badRequest().body("A senha deve conter pelo menos uma letra maiúscula!");
    }

    if (!Pattern.compile("[0-9]").matcher(password).find()) {
        return ResponseEntity.badRequest().body("A senha deve conter pelo menos um número!");
    }

    return ResponseEntity.ok("Senha válida!");
}


public ResponseEntity<String> changePassword(String oldPassword, String newPassword, String repeatPassword, String realPass) {
    if (!oldPassword.equals(realPass)) {
        return ResponseEntity.badRequest().body("A senha antiga não corresponde!");
    }

    ResponseEntity<String> passwordValidationResponse = verify(newPassword);
    if (!passwordValidationResponse.getStatusCode().is2xxSuccessful()) {
        return passwordValidationResponse;  
    }

    if (!newPassword.equals(repeatPassword)) {
        return ResponseEntity.badRequest().body("As senhas novas não coincidem!");
    }

    return ResponseEntity.ok("Senha alterada com sucesso!");
}


}
