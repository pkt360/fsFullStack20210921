package com.example.infrastructure.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;

public interface FilmRepository extends JpaRepository<Film, Integer> {

	<T> List<T> findByFilmIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByFilmIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByFilmIdIsNotNull(Pageable pageable, Class<T> type);
	
	@Query("Select fa.actor FROM FilmActor fa WHERE fa.film.filmId = ?1 ")
	List<Actor> getFilmActores(int id);
	
	@Query("Select f.language.name FROM Film f WHERE f.filmId = ?1 ")
	List<String> getFilmLanguages(int id);
	
	@Query("Select fc.category.name FROM FilmCategory fc WHERE fc.film.filmId = ?1 ")
	List<String> getFilmCategorias(int id);
	
	List<Film> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
}
