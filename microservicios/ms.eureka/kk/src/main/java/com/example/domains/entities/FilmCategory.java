package com.example.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the film_category database table.
 * 
 */
@Entity
@Table(name="film_category")
@NamedQuery(name="FilmCategory.findAll", query="SELECT f FROM FilmCategory f")
public class FilmCategory extends com.example.domains.core.EntityBase implements Serializable {
	private static final long serialVersionUID = 1L;
	private FilmCategoryPK id;
	private Timestamp lastUpdate;
	private Category category;
	private Film film;

	public FilmCategory() {
	}


	@EmbeddedId
	public FilmCategoryPK getId() {
		return this.id;
	}

	public void setId(FilmCategoryPK id) {
		this.id = id;
	}


	@Column(name="last_update")
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id")
	public Film getFilm() {
		return this.film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

}