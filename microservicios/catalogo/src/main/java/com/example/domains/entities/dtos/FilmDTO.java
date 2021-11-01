package com.example.domains.entities.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.example.domains.entities.Film;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "PeliculaDetalles", description = "Version completa de las peliculas")
@Data @AllArgsConstructor @NoArgsConstructor
public class FilmDTO {
	@ApiModelProperty(value = "Identificador de la pelicula", required = true, accessMode = AccessMode.READ_ONLY)
	@JsonProperty("id")
	private int filmId;
	@ApiModelProperty(value = "Titulo de la pelicula", required = true, allowableValues = "Un maximo de 255 caracteres")
	@JsonProperty("titulo")
	private String title;
	@ApiModelProperty(value = "Descripcion de la pelicula")
	@JsonProperty("descripcion")
	private String description;
	@JsonProperty("idioma")
	private String language;
	private String languageVO; 
	@ApiModelProperty(value = "Duración de la pelicula", required = true)
	private int length;
	@ApiModelProperty(value = "Clasificación de la pelicula", allowableValues = "G, PG, PG-13, R, NC-17")
	private String rating;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
	private Short releaseYear;
	@JsonProperty("duracion")
	private byte rentalDuration;
	@JsonProperty("rate")
	private BigDecimal rentalRate;
	@JsonProperty("coste")
	private BigDecimal replacementCost;
	private List<String> actors;
	private List<String> categories;


		public static FilmDTO from(Film source) {
			return new FilmDTO(
					source.getFilmId(),
					source.getTitle(),
					source.getDescription(),
					source.getLanguage() == null ? null : source.getLanguage().getName(),
					source.getLanguageVO() == null ? null : source.getLanguageVO().getName(),
					source.getLength(),
					source.getRating(),
					source.getReleaseYear(),
					source.getRentalDuration(),
					source.getRentalRate(),
					source.getReplacementCost(),
					source.getFilmActors().stream().map(item -> item.getActor().getFirstName() + " " + item.getActor().getLastName())
					.sorted()
					.collect(Collectors.toList()),
				source.getFilmCategories().stream().map(item -> item.getCategory().getName())
					.collect(Collectors.toList())
					);					
	}
	
}
