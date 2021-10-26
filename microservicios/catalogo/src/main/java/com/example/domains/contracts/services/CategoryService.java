package com.example.domains.contracts.services;

import java.util.List;

import com.example.domains.core.services.contracts.DomainService;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;

public interface CategoryService extends DomainService<Category, Integer> {
	List<Film> getFilmCategories(int id);
}
