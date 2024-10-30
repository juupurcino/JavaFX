package com.example.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Exponential;


@RestController
@CrossOrigin(origins = {"http://localhost:5257"})
@RequestMapping("/imaexp")
public class ImaginaryExponentialController {
    
    @GetMapping
    public Exponential conta (@RequestParam Double A,  @RequestParam Double b) {
        
        double value1 = A * Math.cos(b);
        double value2 = A * Math.sin(b);


        return new Exponential(value1, value2);
    }

    
    
}
