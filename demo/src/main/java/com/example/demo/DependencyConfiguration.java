package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dto.Token;
import com.example.demo.impl.DefaultJWTService;
import com.example.demo.impl.ExampleLoginService;
import com.example.demo.impl.ExamplePassService;
import com.example.demo.impl.ExamplePolindromoService;
import com.example.demo.impl.ProductImpl;
import com.example.demo.services.JWTService;
import com.example.demo.services.LoginService;
import com.example.demo.services.PassService;
import com.example.demo.services.PolindromoService;
import com.example.demo.services.ProductService;

@Configuration
public class DependencyConfiguration {
    
    @Bean
    @Scope("singleton")
    // @Scope("prototype")
    // @Scope("request")
    // @Scope("session")
    public LoginService loginService() {
        return new ExampleLoginService("don", "ferrari");
    }

    @Bean
    @Scope("singleton")
    // @Scope("prototype")
    // @Scope("request")
    // @Scope("session")
    public PolindromoService polindromoService() {
        return new ExamplePolindromoService();
    }

    @Bean
    @Scope("singleton")
    // @Scope("prototype")
    // @Scope("request")
    // @Scope("session")
    public PassService passService() {
        return new ExamplePassService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public JWTService<Token> jwtService() {
        return new DefaultJWTService();
    }

    @Bean
    @Scope("singleton")
    public ProductService productService() {
        return new ProductImpl();
    }



}
