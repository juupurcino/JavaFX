package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SumResult;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/{a}:{b}")
    public SumResult test(@PathVariable Integer a, 
                        Integer b){

        if (b == null) {
            b = 0;
        }

        var result = a + b;
        var isEvent = result % 2 == 0;

        return new SumResult(result, isEvent);
    }
    
}



