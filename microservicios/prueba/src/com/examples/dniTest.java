package com.examples;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class dniTest {
		dni validator;


		@BeforeEach
		void setUp() throws Exception {
			validator = new dni();
		}

		@ParameterizedTest(name="NIF Valido: {0}")
		@NullSource
		@ValueSource(strings = { "12345678z", "12345678Z", "1234S" })
		void valid_test(String value) {
			assertTrue(validator.esDni(value));
		}
		
		@ParameterizedTest(name="NIF Invalido: {0}")
		@EmptySource
		@ValueSource(strings = { "12345678", "Z", "1234J" })
		void invalid_test(String value) {
			assertFalse(validator.esDni(value));
		}

		
		/*
		dni DNI;
		
		@Test
		@DisplayName("DNI")
		void testesNif() {
			assertAll("Cadenas DNI",
				() -> assertTrue(true, DNI.esNif("26803422B"), "la primera - DNI correcto"),	
				() -> assertNull(false, DNI.esNif(""), "la segunda - cadena vacía"),
				() -> assertFalse(false, DNI.esNif("26803422"), "la tercera - DNI sin letra"),
				() -> assertFalse(false, DNI.esNif("26803422GB"), "la cuarta - DNI con dos letras"),
				() -> assertFalse(false, DNI.esNif("26803422v"), "la quinta - DNI con letra minúscula"),
				() -> assertFalse(false, DNI.esNif("268034224"), "la sexta - DNI con más números"),
				() -> assertFalse(false, DNI.esNif("2680342B"), "la tercera - DNI con menos")				
			);
			
		}
	*/

}
