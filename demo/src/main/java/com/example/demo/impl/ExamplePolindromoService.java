package com.example.demo.impl;

import com.example.demo.services.PolindromoService;

public class ExamplePolindromoService implements PolindromoService {

    @Override
    public Boolean polindromo(String word) {
        int i = 0, j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

}

    

