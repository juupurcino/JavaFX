package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.impl.ExampleLoginService;
import com.example.demo.impl.ExamplePolindromoService;
import com.example.demo.services.LoginService;
import com.example.demo.services.PolindromoService;

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

}
