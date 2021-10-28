package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.infraestructure.repositories.FilmRepository;
import com.example.infraestructure.repositories.LanguageRepository;

public class LanguageServiceImpl implements LanguageService{
	@Autowired
	private LanguageRepository dao;
	
	@Override
	public List<Language> getAll() {
		return dao.findAll(Sort.by("name"));
	}


	@Override
	public Optional<Language> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsString());
		if(getOne(item.getLanguageId()).isPresent())
			throw new DuplicateKeyException();
		return dao.save(item);
	}

	@Override
	public Language modify(Language item) throws NotFoundException, InvalidDataException {
		if(item == null)
			throw new InvalidDataException("Faltan los datos");
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsString());
		if(getOne(item.getLanguageId()).isEmpty())
			throw new NotFoundException();
		return dao.save(item);
	}

	@Override
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	@Override
	public void delete (Language item) throws InvalidDataException{
		if (item == null) {
			throw new InvalidDataException("Faltan datos");
		}
		dao.deleteById(item.getLanguageId());
	}

	@Override
	public List<Film> getLanguageFilms(int id){
		return dao.getLanguageFilms(id);
	}
	
	
}
