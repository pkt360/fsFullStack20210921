package com.examples;

public class Calculadora implements Calc {
	@Override
	public double suma(double a, double b) {
		return a + b;
	}
	@Override
	public double divide(double a, double b) {
		return a / b;
	}
	@Override
	public int divide(int a, int b) {
		return a / b;
	}
	@Override
	public Double multiplica(double a, double b) {
		return a * b;
	}
}
