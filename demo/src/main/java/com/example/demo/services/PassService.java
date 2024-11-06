package com.example.demo.services;

public interface PassService {
    String verify(String password);
    String alterPass(String oldPassword, String newPassword, String repeatPassword, String realPass);
}
