package com.example.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domains.entities.Country;


public interface CountryRepository extends JpaRepository<Country, Integer>{

}
