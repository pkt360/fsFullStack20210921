package com.examples;

import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


class CalculadoraTest {
	Calculadora calc;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		calc = new Calculadora();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSuma() {
		assertEquals(4, calc.suma(2, 2));
	}
	
	@org.junit.jupiter.api.Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class Divisiones{
		@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
		void test_Divide_Double_Double() {
			assertEquals(0.5, calc.divide(1.0, 2));
			//assertThrows(Exception.class, () -> calc.divide(1.0, 0));
			assertEquals(Double.POSITIVE_INFINITY, calc.divide(1.0, 0));
//			var d = 1/0;
			assertAll("Divisiones enteras",
					() -> assertEquals(0,  calc.divide(1.0, 2), "la "),	
				() -> assertEquals(0,  calc.divide(1, 2), "la entera"),
				() -> assertEquals(Double.POSITIVE_INFINITY, calc.divide(1.0, 0))
			);
		}
		
		@Test
		@DisplayName("Division entera")
		void testDivideIntInt() {
			assertEquals(0, calc.divide(1, 2));
			try {
				assertEquals(0,  calc.divide(1, 0), "la primera");
			} catch (Exception e) {
				fail("Excepcion no controlada");
			}
			assertEquals(0,  calc.divide(0, 0), "la segunda");
			assertThrows(Exception.class, () -> calc.divide(1, 0));
		}
		 
	}

	
	
	

}
