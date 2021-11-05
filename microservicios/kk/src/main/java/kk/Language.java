package kk;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@Table(name="language")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language extends com.example.domains.core.EntityBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="last_update")
	private Timestamp lastUpdate;

	private String name;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language")
	private List<Film> films;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="languageVO")
	private List<Film> filmVO;

	public Language() {
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilms() {
		return this.films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public Film addFilm(Film film) {
		getFilms().add(film);
		film.setLanguage(this);

		return film;
	}

	public Film removeFilm(Film film) {
		getFilms().remove(film);
		film.setLanguage(null);

		return film;
	}

	public List<Film> getFilmVO() {
		return this.filmVO;
	}

	public void setFilmVO(List<Film> filmVO) {
		this.filmVO = filmVO;
	}

	public Film addFilmVO(Film filmVO) {
		getFilmVO().add(filmVO);
		filmVO.setLanguageVO(this);

		return filmVO;
	}

	public Film removeFilmVO(Film filmVO) {
		getFilmVO().remove(filmVO);
		filmVO.setLanguageVO(null);

		return filmVO;
	}

}