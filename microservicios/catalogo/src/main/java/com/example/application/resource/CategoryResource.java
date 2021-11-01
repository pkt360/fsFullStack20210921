package com.example.application.resource;

import java.net.URI;
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

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.domains.entities.dtos.FilmShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.http.HttpStatus;

@RestController
@Api(value = "/categorias", description = "Mantenimiento de categorias", produces = "application/json, application/xml", consumes = "application/json, application/xml")

@RequestMapping(path = "/categorias")
public class CategoryResource {
	@Autowired
	CategoryService srv;

	@ApiOperation(value = "Listado de categorias")	
	@GetMapping
	public List<Category> getAll() {

		return srv.getAll();
	}

	@ApiOperation(value = "Recuperar los datos de una categoria")
	@ApiResponses({ @ApiResponse(code = 200, message = "Categoria encontrada"),
			@ApiResponse(code = 404, message = "Categoria no encontrada") })
	@GetMapping(path = "/{id}")
	public Category getOne(@PathVariable int id) throws NotFoundException {
		var category = srv.getOne(id);
		if (category.isEmpty())
			throw new NotFoundException();
		else
			return category.get();
	}

	@ApiOperation(value = "Listado de peliculas de la misma categoria")
	@ApiResponses({ @ApiResponse(code = 200, message = "Categoria encontrada"),
			@ApiResponse(code = 404, message = "Categoria no encontrada") })
	@GetMapping(path = "/{id}/peliculas")
	@Transactional
	public List<FilmShort> getPelis(@PathVariable int id) throws NotFoundException {
		var category = srv.getOne(id);
		if (category.isEmpty())
			throw new NotFoundException();
		else {
			return srv.getFilmCategories(id).stream().map(item -> FilmShort.from(item)).collect(Collectors.toList());
		}
	}
	@ApiOperation(value = "Añadir una nueva categoria")
	@ApiResponses({ @ApiResponse(code = 200, message = "Categoria añadida"),
			@ApiResponse(code = 404, message = "Categoria no encontrada") })
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Category item)
			throws BadRequestException, DuplicateKeyException, InvalidDataException {
		if (item == null)
			throw new BadRequestException("Faltan los datos");
		var newItem = srv.add(item);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();

	}

	@ApiOperation(value = "Modificar una categoria existente")
	@ApiResponses({ @ApiResponse(code = 200, message = "Categoria modificada"),
			@ApiResponse(code = 404, message = "Categoria no encontrada") })
	@PutMapping("/{id}")
	// @ResponseStatus(HttpStatus.NO_CONTENT)
	public Category update(@PathVariable int id, @Valid @RequestBody Category item)
			throws BadRequestException, NotFoundException, InvalidDataException {
		if (item == null)
			throw new BadRequestException("Faltan los datos");
		if (id != item.getCategoryId())
			throw new BadRequestException("No coinciden los identificadores");
		return srv.modify(item);
	}

	@ApiOperation(value = "Borrar una categoria existente")
	@ApiResponses({ @ApiResponse(code = 200, message = "Categoria borrada"),
			@ApiResponse(code = 404, message = "Categoria no encontrada") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}

}