package com.example.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film extends com.example.domains.core.EntityBase implements Serializable {
	private static final long serialVersionUID = 1L;
	private int filmId;
	private String description;
	private Timestamp lastUpdate;
	private int length;
	private String rating;
	private Short releaseYear;
	private byte rentalDuration;
	private BigDecimal rentalRate;
	private BigDecimal replacementCost;
	private String title;
	private Language language;
	private Language languageVO;
	private List<FilmActor> filmActors;
	private List<FilmCategory> filmCategories;

	public Film() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id")
	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}


	@Lob
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name="last_update")
	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}


	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}


	@Column(name="release_year")
	public Short getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Short releaseYear) {
		this.releaseYear = releaseYear;
	}


	@Column(name="rental_duration")
	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}


	@Column(name="rental_rate")
	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}


	@Column(name="replacement_cost")
	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id")
	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}


	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="original_language_id")
	public Language getLanguageVO() {
		return this.languageVO;
	}

	public void setLanguageVO(Language languageVO) {
		this.languageVO = languageVO;
	}


	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="film")
	public List<FilmActor> getFilmActors() {
		return this.filmActors;
	}

	public void setFilmActors(List<FilmActor> filmActors) {
		this.filmActors = filmActors;
	}

	public FilmActor addFilmActor(FilmActor filmActor) {
		getFilmActors().add(filmActor);
		filmActor.setFilm(this);

		return filmActor;
	}

	public FilmActor removeFilmActor(FilmActor filmActor) {
		getFilmActors().remove(filmActor);
		filmActor.setFilm(null);

		return filmActor;
	}


	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="film")
	public List<FilmCategory> getFilmCategories() {
		return this.filmCategories;
	}

	public void setFilmCategories(List<FilmCategory> filmCategories) {
		this.filmCategories = filmCategories;
	}

	public FilmCategory addFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().add(filmCategory);
		filmCategory.setFilm(this);

		return filmCategory;
	}

	public FilmCategory removeFilmCategory(FilmCategory filmCategory) {
		getFilmCategories().remove(filmCategory);
		filmCategory.setFilm(null);

		return filmCategory;
	}

}