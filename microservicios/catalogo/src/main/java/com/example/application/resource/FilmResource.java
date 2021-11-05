package com.example.application.resource;

import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.domains.entities.dtos.FilmEdit;
import com.example.domains.entities.dtos.FilmShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.infrastructure.repositories.FilmRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;

@RestController
@Api(value = "/peliculas", description = "Mantenimiento de peliculas", produces = "application/json, application/xml", consumes = "application/json, application/xml")
@RequestMapping(path = "/peliculas")
public class FilmResource {
	@Autowired
	FilmService srv;
	@Autowired
	FilmRepository dao;

	@ApiOperation(value = "Listado con la versión mínima de las peliculas")
	@GetMapping(params = "mode=short")
	public List<FilmShort> getAll(
			@ApiParam(allowEmptyValue = true, required = false, allowableValues = "details,short") @RequestParam(required = false) String sort) {
		if (sort == null)
			return srv.getByProjection(FilmShort.class);
		else
			return (List<FilmShort>) srv.getByProjection(Sort.by(sort), FilmShort.class);
	}

	@ApiOperation(value = "Listado de las peliculas")
	@GetMapping(params = "page")
	public Page<FilmDTO> getAllPageable(Pageable item) {
		return srv.getByProjection(item, FilmDTO.class);
	}

	@GetMapping(path = "/{id}", params = "mode=short")
	@ApiOperation(value = "Recupera la versión mínima de una pelicula")
	@ApiResponses({ @ApiResponse(code = 200, message = "Pelicula encontrada"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	public FilmShort getOneCorto(
			@ApiParam(value = "Identificador de la pelicula", required = true) @PathVariable int id,
			@ApiParam(required = false, allowEmptyValue = true, allowableValues = "details,short,edit", defaultValue = "edit") @RequestParam(required = false, defaultValue = "edit") String mode)
			throws Exception {
		Optional<Film> result = dao.findById(id);
		if (!result.isPresent())
			throw new NotFoundException();
		return FilmShort.from(result.get());
	}

	@ApiOperation(value = "Recupera la versión completa de una pelicula")
	@GetMapping(path = "/{id}", params = "mode=details")
	@ApiResponses({ @ApiResponse(code = 200, message = "Pelicula encontrada"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	public FilmDTO getOneDetalle(
			@ApiParam(value = "Identificador de la pelicula", required = true) @PathVariable int id,
			@ApiParam(required = false, allowableValues = "details,short,edit", defaultValue = "edit") @RequestParam(required = false, defaultValue = "edit") String mode)
			throws Exception {
		Optional<Film> rslt = dao.findById(id);
		if (!rslt.isPresent())
			throw new NotFoundException();
		return FilmDTO.from(rslt.get());
	}

	@ApiOperation(value = "Recupera la versión editable de una pelicula")
	@GetMapping(path = "/{id}")
	@ApiResponses({ @ApiResponse(code = 200, message = "Pelicula encontrada"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	public FilmEdit getOne(@ApiParam(value = "Identificador de la pelicula", required = true) @PathVariable int id,
			@ApiParam(required = false, allowableValues = "details,short,edit", defaultValue = "edit") @RequestParam(required = false, defaultValue = "edit") String mode)
			throws Exception {
		Optional<Film> result = srv.getOne(id);
		if (!result.isPresent())
			throw new NotFoundException();
		else
			return FilmEdit.from(result.get());
	}

	@ApiOperation(value = "Listado de los actores de la pelicula")
	@ApiResponses({ @ApiResponse(code = 200, message = "Pelicula encontrada"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	@GetMapping(path = "/{id}/actores")
	@Transactional
	public List<ActorDTO> getActores(@PathVariable int id) throws NotFoundException {
		Optional<Film> film = srv.getOne(id);
		if (film.isEmpty())
			throw new NotFoundException();
		else {
			return srv.getFilmActores(id).stream().map(item -> ActorDTO.from(item)).collect(Collectors.toList());
		}
	}

	@ApiOperation(value = "Listado de los idiomas de la pelicula")
	@ApiResponses({ @ApiResponse(code = 200, message = "Pelicula encontrada"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	@GetMapping(path = "/{id}/idiomas")
	@Transactional
	public List<String> getLanguages(@PathVariable int id) throws NotFoundException {
		var film = srv.getOne(id);
		if (film.isEmpty())
			throw new NotFoundException();
		else {
			return srv.getFilmLanguages(id);
		}
	}

	@ApiOperation(value = "Listado de las categorias de la pelicula")
	@ApiResponses({ @ApiResponse(code = 200, message = "Pelicula encontrada"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	@GetMapping(path = "/{id}/categorias")
	@Transactional
	public List<String> getCategories(@PathVariable int id) throws NotFoundException {
		var film = srv.getOne(id);
		if (film.isEmpty())
			throw new NotFoundException();
		else {
			return srv.getFilmCategorias(id);
		}
	}

	@ApiOperation(value = "Añadir una nueva pelicula")
	@ApiResponses({ @ApiResponse(code = 201, message = "Pelicula añadida"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Object> create(@Valid @RequestBody FilmEdit item) throws Exception {
		Film result = FilmEdit.from(item);
		if (result.isInvalid())
			throw new InvalidDataException(result.getErrorsString());
		if (dao.findById(item.getFilmId()).isPresent()) {
			throw new InvalidDataException("Duplicate key");
		}
		var film = dao.save(result);
		item.getActors().stream().forEach(id -> result.addFilmActor(new Actor(id)));
		item.getCategories().stream().forEach(id -> result.addFilmCategory(new Category(id)));
		dao.save(result);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getFilmId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Modificar una pelicula existente", notes = "Los identificadores deben coincidir")
	@ApiResponses({ @ApiResponse(code = 200, message = "Pelicula encontrada"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	@Transactional
	@PutMapping(path = "/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	public FilmEdit update(@PathVariable int id, @Valid @RequestBody FilmEdit item) throws Exception {
		if (item.getFilmId() != id)
			throw new BadRequestException("No coinciden los identificadores");
		Film result = FilmEdit.from(item);
		if (result.isInvalid())
			throw new InvalidDataException(result.getErrorsString());
		Optional<Film> act = dao.findById(item.getFilmId());
		if (!act.isPresent())
			throw new NotFoundException("No se encuentra el objeto");
		result = dao.save(item.update(act.get()));
		return FilmEdit.from(result);
	}

	@ApiOperation(value = "Borrar una pelicula existente")
	@ApiResponses({ @ApiResponse(code = 204, message = "Pelicula borrada"),
			@ApiResponse(code = 404, message = "Pelicula no encontrada") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@ApiParam(value = "Identificador de la pelicula", required = true) @PathVariable int id)
			throws Exception {
		try {
			dao.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("No se encuentra el objeto", e);
		}
	}

	public List<Film> novedades(Timestamp fecha) {
		return srv.findByLastUpdateGreaterThanEqualOrderByLastUpdate(fecha);
	}
}