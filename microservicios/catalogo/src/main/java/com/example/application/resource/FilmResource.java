package com.example.application.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.example.domains.entities.Category;
import com.example.domains.entities.Language;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.FilmDTO;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.domains.contracts.services.FilmService;


@RestController
@RequestMapping(path = "/peliculas")
public class FilmResource {
	
	@Autowired
	FilmService srv;
	
	@GetMapping
	public List<FilmDTO> getAll(@RequestParam(required = false) String sort) {
		if(sort== null)
			return srv.getByProjection(FilmDTO.class);
		else
			return (List<FilmDTO>) srv.getByProjection(Sort.by(sort), FilmDTO.class);
	}
	
	@GetMapping(params = "page")
	public Page<FilmDTO> getAllPageable(Pageable item) {
		return srv.getByProjection(item, FilmDTO.class);
	}

	@GetMapping(path = "/{id}")
	public FilmDTO getOne(@PathVariable int id) throws NotFoundException {
		var film = srv.getOne(id);
		if(film.isEmpty())
			throw new NotFoundException();
		else
			return FilmDTO.from(film.get());
	}
	
	@GetMapping(path = "/{id}/actores")
	@Transactional
	public List<ActorDTO> getActores(@PathVariable int id) throws NotFoundException {
		var film = srv.getOne(id);
		if(film.isEmpty())
			throw new NotFoundException();
		else {
			return srv.getFilmActores(id).stream().map(item -> ActorDTO.from(item)).collect(Collectors.toList());
		}
	}
	
	@GetMapping(path = "/{id}/idiomas")
	@Transactional
	public List<Language> getLanguages(@PathVariable int id) throws NotFoundException {
		var film = srv.getOne(id);
		if(film.isEmpty())
			throw new NotFoundException();
		else {
			return srv.getFilmLanguages(id);
		}
	}
	
	@GetMapping(path = "/{id}/categorias")
	@Transactional
	public List<Category> getCategories(@PathVariable int id) throws NotFoundException {
		var film = srv.getOne(id);
		if(film.isEmpty())
			throw new NotFoundException();
		else {
			return srv.getFilmCategorias(id);
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody FilmDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new BadRequestException("Faltan los datos");
		var newItem = srv.add(FilmDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getFilmId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public FilmDTO update(@PathVariable int id, @Valid @RequestBody FilmDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if(item == null)
			throw new BadRequestException("Faltan los datos");
		if(id != item.getFilmId())
			throw new BadRequestException("No coinciden los identificadores");
		return FilmDTO.from(srv.modify(FilmDTO.from(item)));	
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}

}
