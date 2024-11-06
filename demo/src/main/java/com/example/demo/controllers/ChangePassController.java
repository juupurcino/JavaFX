package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PassData;
import com.example.demo.impl.ExamplePassService;
import com.example.demo.model.User;
import com.example.demo.repositores.ChangePassRepository;

@RestController
@RequestMapping("/changepassword")
public class ChangePassController {

    @Autowired
    private ChangePassRepository passRepository;

    @Autowired
    private ExamplePassService passService;

    @PatchMapping
    public ResponseEntity<String> changePassword(@RequestBody PassData data) {

        User user = passRepository.findByUsername(data.username());
        if (user == null) {
            return ResponseEntity.badRequest().body("Usuário não encontrado!");
        }

        String passwordValidationResponse = passService.alterPass(data.password(), data.newPassword(), data.repeatPassword(), user.getPassword());

        if (!passwordValidationResponse.equals("ok")){
            return ResponseEntity.ok(passwordValidationResponse);
        }    

        user.setPassword(data.newPassword());
        passRepository.save(user);

        return ResponseEntity.ok("Senha alterada com sucesso!");

    }
}
