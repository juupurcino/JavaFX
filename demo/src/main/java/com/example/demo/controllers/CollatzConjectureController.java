package com.example.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Collatz;

@RestController
@CrossOrigin(origins = {"http://localhost:5257"})
@RequestMapping("/collatz")
public class CollatzConjectureController {

    @GetMapping
    public Collatz result(@RequestParam int step, @RequestParam int current) {

        for (int i = 0; i < step; i++) {
            
            if (current % 2 == 0) {
                current = current / 2;
            } else {
                current = 3 * current + 1;
            }
        }
        return new Collatz(current);
    }
}


