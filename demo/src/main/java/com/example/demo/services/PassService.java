package com.example.demo.services;

import org.springframework.http.ResponseEntity;

public interface PassService {
    ResponseEntity<String> verify(String password);
}
