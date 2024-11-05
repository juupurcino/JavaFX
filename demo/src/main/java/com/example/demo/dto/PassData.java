package com.example.demo.dto;

public record PassData(
    String username,
    String password,  
    String newPassword,  
    String repeatPassword  
) {}
