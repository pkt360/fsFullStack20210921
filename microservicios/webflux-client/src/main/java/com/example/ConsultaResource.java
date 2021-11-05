package com.example;

import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ConsultaResource {
	@Data @AllArgsConstructor @NoArgsConstructor
	public static class Contacto {
		private int id;
		private String tratamiento;
		private String nombre;
		private String apellidos;
		private String telefono;
		private String email;
		private String sexo;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		private Date nacimiento;
		private String avatar;
		private boolean conflictivo = false;
	}

	@GetMapping(path = "/serie")
	public Flux<Contacto> enSerie() {
		RestTemplate rest = new RestTemplate();
		Contacto contacto1 = rest.getForObject("http://localhost:8066/api/contactos/1", Contacto.class);
		Contacto contacto2 = rest.getForObject("http://localhost:8066/api/contactos/2", Contacto.class);
		Contacto contacto3 = rest.getForObject("http://localhost:8066/api/contactos/3", Contacto.class);
		return Flux.just(contacto1, contacto2, contacto3);
	}
	@GetMapping(path = "/paralelo")
	public Flux<Contacto> enParalelo() {
		WebClient client = WebClient.create("http://localhost:8066");
		Mono<Contacto> contacto1 = client.get()
				  .uri("/api/contactos/{id}", "1")
				  .retrieve()
				  .bodyToMono(Contacto.class);
		Mono<Contacto> contacto2 = client.get()
				  .uri("/api/contactos/{id}", "2")
				  .retrieve()
				  .bodyToMono(Contacto.class);
		Mono<Contacto> contacto3 = client.get()
				  .uri("/api/contactos/{id}", "3")
				  .retrieve()
				  .bodyToMono(Contacto.class);
		return Flux.merge(contacto1, contacto2, contacto3);
	}

}
