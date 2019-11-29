package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomTestt {

	@Test
	void equalstest() {
		Polynom a = new Polynom("5x+7-5x^2");
		Polynom b = new Polynom("x+7-5x^2");
		Polynom c = new Polynom("5x^3+7-5x^2");
		Polynom d = new Polynom("-5x-4x+5x^2+0");
		
		
	}

}
