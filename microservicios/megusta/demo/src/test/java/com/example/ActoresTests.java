package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.example.application.resource.ActorResource;
import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.services.ActorServiceImpl;
import com.example.infraestructure.repositories.ActorRepository;
import com.example.ioc.Calculadora;
import com.example.ioc.Formato;
import com.example.ioc.Servicio;
import com.example.ioc.ServicioMockImpl;

@SpringBootTest
class ActoresTests {
	
	@Nested
	class Entidad {
		@Test
		public void actor_valido() {
			Actor actor = new Actor(1, "Pepito", "Grillo");
			assertTrue(actor.isValid());
		}
		@Test
		public void actor_invalido_nombre() {
			Actor actor = new Actor(1, "", "Grillo");
			assertTrue(actor.isInvalid());
		}
	}

	@Nested
	class Repositorio {
		@Autowired
		ActorRepository dao;
		@Test
		public void findByFirstNameStartingWithOrderByLastNameDesc() {
			var rslt = dao.findByFirstNameStartingWithOrderByLastNameDesc("a");
			assertNotNull(rslt);
		}
	}
	
	@Nested
	class ServicioDominio {
		@MockBean
		ActorRepository dao;
		@Autowired
		ActorServiceImpl srv;
		
		@Test
		public void getAll() {
			when(dao.findAll()).thenReturn(List.of(new Actor(1, "Pepito", "Grillo")));
			assertEquals(1, srv.getAll());
		}
	}	
	
	@Nested
	class Recurso {
		@MockBean
		ActorService srv;
		@Autowired
		ActorResource rest;
		@Test
		public void getAll() {
			when(srv.getAll()).thenReturn(List.of(new Actor(1, "Pepito", "Grillo")));
			assertEquals(1, rest.getAll(null));
		}
	}	

}
