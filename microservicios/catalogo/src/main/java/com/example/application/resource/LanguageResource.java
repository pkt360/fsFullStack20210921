package com.example.application.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Language;
import com.example.domains.entities.dtos.FilmShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@RestController
@RequestMapping(path="/idiomas")
public class LanguageResource {
	
	@Autowired
	LanguageService srv;
	
	@GetMapping
	public List<Language> getAll() {
		return srv.getAll();		
	}
	
	
	@GetMapping(path = "/{id}")
	public Language getOne(@PathVariable int id) throws NotFoundException {
		var language = srv.getOne(id);
		if(language.isEmpty())
			throw new NotFoundException();
		else
			return language.get();
	}
	
	@GetMapping(path = "/{id}/peliculas")
	@Transactional
	public List<FilmShort> getPelis(@PathVariable int id) throws NotFoundException {
		var language = srv.getOne(id);
		if(language.isEmpty())
			throw new NotFoundException();
		else {
			return srv.getLanguageFilms(id).stream().map(item -> FilmShort.from(item)).collect(Collectors.toList());
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Language item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		if(item == null)
			throw new BadRequestException("Faltan los datos");
		var newItem = srv.add(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getLanguageId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping("/{id}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public Language update(@PathVariable int id, @Valid @RequestBody Language item) throws BadRequestException, NotFoundException, InvalidDataException {
		if(item == null)
			throw new BadRequestException("Faltan los datos");
		if(id != item.getLanguageId())
			throw new BadRequestException("No coinciden los identificadores");
		return srv.modify((item));	
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}
