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
class Nif_Validator_Test {
	NifValidator validator;

	@BeforeEach
	void setUp() throws Exception {
		validator = new NifValidator();
	}

	@ParameterizedTest(name="NIF Valido: {0}")
	@NullSource
	@ValueSource(strings = { "12345678z", "12345678Z", "1234S" })
	void valid_test(String value) {
		assertTrue(validator.isValid(value));
	}
	
	@ParameterizedTest(name="NIF Invalido: {0}")
	@EmptySource
	@ValueSource(strings = { "12345678", "Z", "1234J" })
	void invalid_test(String value) {
		assertFalse(validator.isValid(value));
	}

}
