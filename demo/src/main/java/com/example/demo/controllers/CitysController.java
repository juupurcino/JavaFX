package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Citys;
import com.example.demo.repositores.CityRepository;

@RestController
@RequestMapping("/cities")
public class CitysController {

    @Autowired
    private CityRepository repo;

    @GetMapping("/{search}")
    public ResponseEntity<List<Citys>> getByCountry(@PathVariable String search) {
    List<Citys> cities = repo.findByCountry(search);

    return new ResponseEntity<>(cities, HttpStatus.OK);
}

    // @GetMapping("/find/{name}")
    // public ResponseEntity<List<Citys>> getByName(@PathVariable String name) {
    //     List<Citys> cities = repo.findByName(name);

    //     if (cities.isEmpty()) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }

    //     return new ResponseEntity<>(cities, HttpStatus.OK);
    // }
}
