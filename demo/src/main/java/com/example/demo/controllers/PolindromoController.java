package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.WordResult;
import com.example.demo.services.PolindromoService;

@RestController
@RequestMapping("/reverse")
public class PolindromoController {

    @Autowired
    PolindromoService service;

    @GetMapping("/{word}")
    public WordResult checkPalindrome(@PathVariable String word) {
    
        return new WordResult(word, service.polindromo(word));
    }

    
    
    
}
