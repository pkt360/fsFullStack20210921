package com.examples;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
public class MockTest {
//	@Mock
//	Calculadora calculadora;
	
	@Test
	void suma_mock() {
		Calc calculadora = mock(Calculadora.class); 
		assertNotNull(calculadora);
//		when(calculadora.suma(2, 2)).thenReturn(2.0);
//		
//		assertEquals(calculadora.suma(2, 2), 2);
	}

}
