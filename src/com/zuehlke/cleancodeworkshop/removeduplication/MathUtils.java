package com.zuehlke.cleancodeworkshop.removeduplication;

public class MathUtils {

	private MathUtils() {
	}

	public static boolean isDivisibleBy(int number, int divisor) {
		return number % divisor == 0;
	}
}
