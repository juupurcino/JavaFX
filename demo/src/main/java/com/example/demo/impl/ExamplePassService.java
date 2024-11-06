package com.example.demo.impl;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.example.demo.services.PassService;

@Service
public class ExamplePassService implements PassService{
    
    @Override
    public String verify(String password) {
    if (password.length() < 8) {
        return  "A senha deve possuir ao menos 8 caracteres!";
    }

    if (!Pattern.compile("[a-z]").matcher(password).find()) {
        return "A senha deve conter pelo menos uma letra minúscula!";
    }

    if (!Pattern.compile("[A-Z]").matcher(password).find()) {
        return "A senha deve conter pelo menos uma letra maiúscula!";
    }

    if (!Pattern.compile("[0-9]").matcher(password).find()) {
        return "A senha deve conter pelo menos um número!";
    }

    return "ok";
}


@Override
public String alterPass(String oldPassword, String newPassword, String repeatPassword, String realPass) {

    if (!oldPassword.equals(realPass)) {
        return "A senha antiga não corresponde!";
    }

    String passwordValidationResponse = verify(newPassword);
    if (!passwordValidationResponse.equals( "ok")) {
        return passwordValidationResponse;  
    }

    if (!newPassword.equals(repeatPassword)) {
        return"As senhas novas não coincidem!";
    }

    return "ok";
}

}
