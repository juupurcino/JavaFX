package com.example.demo.repositores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Citys;

@Repository
public interface CityRepository extends JpaRepository<Citys, Long> {
    List<Citys> findByCountry(String country);
}
