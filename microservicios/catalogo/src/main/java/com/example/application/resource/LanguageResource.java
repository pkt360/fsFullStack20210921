package com.example.application.resource;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.infrastructure.repositories.LanguageRepository;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;

@RestController
@Api(value = "/idiomas", description = "Mantenimiento de idiomas", produces = "application/json, application/xml", consumes = "application/json, application/xml")
@RequestMapping(path = "/idiomas")
public class LanguageResource {
	@Autowired
	LanguageService srv;
	@Autowired
	private LanguageRepository dao;

	
	@ApiOperation(value = "Listado de idiomas")
	@GetMapping
	public List<Language> getAll() {
		
			return srv.getAll();
	}

	
	@ApiOperation(value = "Recupera los datos de un idioma")
	@GetMapping(path = "/{id}")
	public Language getOne(@PathVariable int id) throws NotFoundException {
		var language = srv.getOne(id);
		if(language.isEmpty())
			throw new NotFoundException();
		else
			return language.get();
	}

	@ApiOperation(value = "Listado de las peliculas del mismo idioma")
	@ApiResponses({ @ApiResponse(code = 200, message = "Idioma encontrado"),
			@ApiResponse(code = 404, message = "Idioma no encontrado") })
	@GetMapping(path = "/{id}/peliculas")
	@Transactional
	public List<FilmShort> getPelis(@PathVariable int id) throws NotFoundException {
		var language= srv.getOne(id);
		if(language.isEmpty())
			throw new NotFoundException();
		else {
			return  srv.getLanguageFilms(id).stream().map(item -> FilmShort.from(item)).collect(Collectors.toList());
		}
	}

	@ApiOperation(value = "Añadir un nuevo idioma")
	@ApiResponses({ @ApiResponse(code = 200, message = "Idioma creado"),
			@ApiResponse(code = 404, message = "Idioma no encontrado") })
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@JsonView(Language.Partial.class)
	public ResponseEntity<Object> create(@Valid @RequestBody Language item) throws Exception {
		if (item.isInvalid())
			throw new InvalidDataException(item.getErrorsString());
		if (dao.findById(item.getLanguageId()).isPresent())
			throw new InvalidDataException("Duplicate key");
		dao.save(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(item.getLanguageId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@ApiOperation(value = "Modificar un idioma existente")
	@ApiResponses({ @ApiResponse(code = 200, message = "Idioma modificado"),
			@ApiResponse(code = 404, message = "Idioma no encontrado") })
	@PutMapping("/{id}")
	@JsonView(Language.Partial.class)
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public Language update(@PathVariable int id, @Valid @RequestBody Language item) throws Exception {
		if(item.isInvalid())
			throw new InvalidDataException(item.getErrorsString());
		if(id != item.getLanguageId())
			throw new BadRequestException("No coinciden los identificadores");
		if (!dao.findById(item.getLanguageId()).isPresent())
			throw new NotFoundException("No se encuentra el objeto");
		return srv.modify(item);	
	}

	@ApiOperation(value = "Eliminar un idioma existente")
	@ApiResponses({ @ApiResponse(code = 200, message = "Idioma borrado"),
			@ApiResponse(code = 404, message = "Idioma no encontrado") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@JsonView(Language.Partial.class)
	public void delete(@PathVariable int id) throws Exception {
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("No se encuentra el objeto", e);
		}
	}

	public List<Language> novedades(Timestamp fecha) {
		return srv.findByLastUpdateGreaterThanEqualOrderByLastUpdate(fecha);
	}

}