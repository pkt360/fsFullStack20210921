package com.example.domains.entities.dtos;

import java.sql.Timestamp;

import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilmDTO {
	@JsonProperty("id")
	private int filmId;
	@JsonProperty("titulo")
	private String title;
	@JsonProperty("idioma")
	private String idioma;
	@JsonProperty("descripcion")
	private String description;
	private Language language;
	@JsonProperty("ultima actualizacion")
	private Timestamp lastUpdate;
	@JsonProperty("duracion alquiler")
	private byte rentalDuration;
	@JsonProperty("rental rate")
	private BigDecimal rentalRate;
	@JsonProperty("coste")
	private BigDecimal replacementCost;
	
	public FilmDTO(int filmId, String title, String description, Language language,
			Timestamp lastUpdate, byte rentalDuration, BigDecimal rentalRate, BigDecimal replacementCost) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.language = language;
		this.lastUpdate = lastUpdate;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
	}
	
	public static Film from(FilmDTO source) {
		return new Film(
			source.getFilmId(),
			source.getTitle(),
			source.getDescription(),
			source.getLanguage(),
			source.getLastUpdate(),
			source.getRentalDuration(),
			source.getRentalRate(),
			source.getReplacementCost()
		);
	}
	
	public static FilmDTO from(Film source) {
		return new FilmDTO(
				source.getFilmId(),
				source.getTitle(),
				source.getDescription(),
				source.getLanguage(),
				source.getLastUpdate(),
				source.getRentalDuration(),
				source.getRentalRate(),
				source.getReplacementCost()	
		);
	}
}
