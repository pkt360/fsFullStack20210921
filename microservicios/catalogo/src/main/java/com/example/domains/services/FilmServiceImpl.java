package com.example.domains.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.infrastructure.repositories.FilmRepository;


@Service
public class FilmServiceImpl implements FilmService {
	@Autowired
	private FilmRepository dao;

	@Override
	public List<Film> getAll() {

		return dao.findAll();
	}

	@Override
	public Iterable<Film> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Film> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findByFilmIdIsNotNull(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findByFilmIdIsNotNull(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findByFilmIdIsNotNull(pageable, type);
	}

	@Override
	public Optional<Film> getOne(Integer id) {

		return dao.findById(id);
	}
	
	@Override
	public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
		if (item == null) {
			throw new InvalidDataException("Faltan los datos");
		}
		if (item.isInvalid()) {
			throw new InvalidDataException(item.getErrorsString());
		}
		if (getOne(item.getFilmId()).isPresent()) {
			throw new DuplicateKeyException();
		}
		return dao.save(item);

	}

	@Override
	public Film modify(Film item) throws NotFoundException, InvalidDataException {
		if (item == null) {
			throw new InvalidDataException("Faltan los datos");
		}
		if (item.isInvalid()) {
			throw new InvalidDataException(item.getErrorsString());
		}

		if (getOne(item.getFilmId()).isEmpty()) {
			throw new NotFoundException();
		}
		return dao.save(item);

	}

	@Override
	public void delete(Film item) throws InvalidDataException {

		if (item == null) {
			throw new InvalidDataException("Faltan los datos");
		}

		dao.deleteById(item.getFilmId());

	}

	@Override
	public void deleteById(Integer id) {

		dao.deleteById(id);
		
		
	}

	@Override
	public List<Actor> getFilmActores(int id) {
		
		return dao.getFilmActores(id);
	}

	@Override
	public List<String> getFilmLanguages(int id) {
		
		return dao.getFilmLanguages(id);
	}
	@Override
	public List<String> getFilmCategorias(int id) {
		
		return dao.getFilmCategorias(id);
	}

	@Override
	public List<Film> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha) {
		
		return dao.findByLastUpdateGreaterThanEqualOrderByLastUpdate(fecha);
	}

}
