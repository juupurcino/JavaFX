package com.example.demo.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface ChangePassRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
