package com.example.infrastructure.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.entities.Category;
import com.example.domains.entities.Film;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	<T> List<T> findByCategoryIdIsNotNull(Class<T> type);
	<T> Iterable<T> findByCategoryIdIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByCategoryIdIsNotNull(Pageable pageable, Class<T> type);
	
	@Query("SELECT f.film FROM FilmCategory f WHERE f.category.categoryId = ?1")
	List<Film> getFilmCategories(int id);
	
	List<Category> findByLastUpdateGreaterThanEqualOrderByLastUpdate(Timestamp fecha);
}
