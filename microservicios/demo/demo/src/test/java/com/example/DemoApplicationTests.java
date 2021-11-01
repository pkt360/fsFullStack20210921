package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.example.ioc.Calculadora;
import com.example.ioc.Formato;
import com.example.ioc.Servicio;
import com.example.ioc.ServicioMockImpl;

@SpringBootTest
//@ActiveProfiles("test")
class DemoApplicationTests {
	@Autowired
//	@Qualifier("uno")
	Servicio srv;
	
	@Autowired
	Formato formato;
	
	@Test
	void inyeccionesTaza() {
		assertEquals("SOY UNA TAZA", srv.saluda());
		assertEquals("HOLA", formato.formatea("hola"));
		assertEquals(1, formato.getCont());
	}

	@Test
	void inyeccionesTetera() {
		assertEquals("Soy una tetera", srv.saluda());
		assertEquals("HOLA", formato.formatea("hola"));
		assertEquals(1, formato.getCont());
	}
	
	public static class IoCTestConfig {
		@Bean
		Servicio getServicio() {
			// return new ServicioImpl();
			return new ServicioMockImpl();
		}
	}

	@Nested
	@ContextConfiguration(classes = IoCTestConfig.class)
	class IoCTest {
		@Autowired
		Servicio srv;

		@Test
		void contextLoads() {
			assertEquals("Soy el doble de prueba", srv.saluda());
		}
	}
	
	@Nested
	class CalculadoraTest {
		@Mock
		Calculadora calculadora;

		@Test
		void contextLoads() {
			when(calculadora.suma(0, 0)).thenReturn(2.0);
			when(calculadora.suma(1, 0)).thenReturn(2.0);
			
			assertEquals(2.0, calculadora.suma(2, 0));
			
		}
	}

}
