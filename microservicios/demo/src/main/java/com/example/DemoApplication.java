package com.example;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.ActorShort;
import com.example.infraestructure.repositories.ActorRepository;
import com.example.ioc.Servicio;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableEurekaClient
@EnableFeignClients("com.example.application.proxies")
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
	@Qualifier("directo")
	public RestTemplate restTemplateDirecto(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	@LoadBalanced
	@Qualifier("balanceado")
	public RestTemplate restTemplateBalanceado(RestTemplateBuilder builder) {
		return builder.build();
	}

//	@Autowired
//	Servicio srv;
//	
//	@Value("${mi.propia.clave}")
//	String name;
//	
	@Autowired
	ActorRepository dao;
	
	@Autowired
	ActorService srv;
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
//		Optional<Actor> a = dao.findById(201);
//		if(a.isPresent())
//			System.out.println(a.get());
//		else {
//			System.out.println("No encontrado");
//		}
		
//		Actor actor= new Actor(0, "Pepito", "Grillo");
//		actor.addFilmActor(new Film(1));
//		Actor actor= a.get();
//		actor.setFirstName(actor.getFirstName().toUpperCase());
//		dao.save(actor);
////		dao.deleteById(201);
//		dao.findAll().forEach(System.out::println);
//		

//		Optional<Actor> a = dao.findById(1);
//		if(a.isPresent()) {
//			System.out.println(a.get());
//			a.get().getFilmActors().forEach(item -> System.out.println(item.getFilm()));
//		} else {
//			System.out.println("No encontrado");
//		}
		//dao.findByFirstNameStartingWithOrderByLastNameDesc("P").forEach(System.out::println);
//		dao.findByLastUpdateGreaterThan(LocalDate.now()).forEach(System.out::println);

//		srv.getAll().forEach(System.out::println);
		// srv.getAll().forEach(item-> System.out.println(ActorDTO.from(item)));
//		var fuera = new ActorDTO(205, "PEPITO", "GRILLO");
//		System.out.println(srv.modify(ActorDTO.from(fuera)));
		
//		dao.findByActorIdNotNull(ActorShort.class)
//			.forEach(item-> System.out.println(item.getNombreCompleto()));
//		dao.findByActorIdIsNotNull(ActorDTO.class)
//		.forEach(item-> System.out.println(item));
//		dao.findByActorIdNotNull(Actor.class)
//		.forEach(item-> System.out.println(item));
			// .forEach(System.out::println);
		// srv.modify(new Actor(333));
//		srv.getAll().forEach(System.out::println);
//		Actor actor= new Actor(0, "", "12345678Z");
//		if(actor.isInvalid())
//			actor.getErrors().forEach(item-> 
//			System.out.println(item.getPropertyPath() + ": " + item.getMessage())
//			);
//		else {
//			System.out.println("Valido");
//		}
//		// dao.save(actor);
//		srv.add(actor);
//		dao.laMia(DateFormat.getDateInstance().parse(DateFormat.getDateInstance().format(new Date()))).forEach(System.out::println);
	}

}
