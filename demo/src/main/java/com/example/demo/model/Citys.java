package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    @Entity
    public class Citys {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;

        @Column 
        private String Country;
        
        @Column 
        private String State;

        @Column 
        private String City;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String country) {
            Country = country;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

    }