package com.examples;

import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

class CalculadoraTest {
	Calc calc;

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

	@ParameterizedTest(name = "Suma {index} => {0} + {1} = {2}")
	@CsvSource({ "2,2,4", "0,0,0", "1,-1,0"/*, "'0.1', '0.2', '0.3'"*/ })
	void testSuma(double a, double b, double rslt) {
		assumeTrue(b >= 0);
		assertEquals(rslt, calc.suma(a, b));
	}
	@ParameterizedTest(name = "Multiplica {index} => {0} * {1} = {2}")
	@CsvSource({ "2,2,4" })
	void testMultiplica(double a, double b, double rslt) {
		assertEquals(rslt, calc.multiplica(a, b));
	}

	@Test
	@Tag("smoke")
	@Tag("lenta")
	void testHumo() {
		assertEquals(3, calc.suma(1, 2));
	}
	@Nested
	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
	class Divisiones {
		Calc calc;

		@BeforeEach
		void setUp() throws Exception {
			calc = new Calculadora();
		}
		
		@RepeatedTest(value = 5, name = "{displayName} {currentRepetition}/{totalRepetitions}")
		void test_Divide_Double_Double() {
//			assertEquals(0.5, calc.divide(1.0, 2));
//			//assertThrows(Exception.class, () -> calc.divide(1.0, 0));
//			assertEquals(Double.POSITIVE_INFINITY, calc.divide(1.0, 0));
////			var d = 1 / 0;
			assertAll("Divisiones enteras", 
				() -> assertEquals(0.5, calc.divide(1.0, 2), "la real"),
//				() -> assertEquals(0.5, calc.divide(1, 2), "La entera"),
				() -> assertEquals(Double.POSITIVE_INFINITY, calc.divide(1.0, 0))
			);
		}

		@Test
		@DisplayName("Division entera")
		@Disabled
		void testDivideIntInt() {
			assertEquals(0, calc.divide(1, 2));
//			try {
//				assertEquals(0, calc.divide(1, 0), "la primera");
//			} catch (Exception e) {
//				fail("Excepcion no controlada");
//			}
//			assertEquals(0, calc.divide(0, 0), "la segunda");
			assertThrows(Exception.class, () -> calc.divide(1, 0));
		}
	}
	
//	@Nested
//	@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//	@ExtendWith(MockitoExtension.class)
//	class Mockeado {
//		@Mock
//		Calculadora calculadora;
//		
//		@Test
//		void suma_mock() {
//			when(calculadora.suma(2, 2)).thenReturn(2.0);
//			
//			assertEquals(calculadora.suma(2, 2), 2);
//		}
//	}
}
