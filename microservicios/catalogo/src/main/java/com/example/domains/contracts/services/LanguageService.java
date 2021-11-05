package com.example.domains.contracts.services;

import java.sql.Timestamp;
import java.util.List;

import com.example.domains.core.services.contracts.DomainService;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

public interface LanguageService extends DomainService<Language, Integer> {
	List<Film> getLanguageFilms(int id);
	
	List<Language> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
}
