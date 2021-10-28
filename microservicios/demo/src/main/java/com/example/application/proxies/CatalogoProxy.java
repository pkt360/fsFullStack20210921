package com.example.application.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.domains.entities.Categoria;

@FeignClient(name="catalogo-service")
public interface CatalogoProxy {
	
	@GetMapping
	String getRaiz();
	
	@GetMapping(path = "/categorias")
	List<Categoria> getCategorias();
	
	@GetMapping(path = "/categorias/{id}")
	List<Categoria> getCategria(@PathVariable int d);
	
	@PostMapping(path = "/categorias")
	void addCategoria(Categoria item);
	
	@PutMapping(path = "/categorias/{id}")
	Categoria modifyCategoria(@PathVariable Integer  id, Categoria item);
	/*
	@DeleteMapping(path = "/categorias/{id}")
	List<Categoria> getCategria(@PathVariable int d);
	*/
}
