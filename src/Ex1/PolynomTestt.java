package Ex1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PolynomTestt {

	@Test
	void isPolynomFromStringTest() {
		Polynom a = new Polynom("5x+7-5x^2");
		Polynom b = new Polynom("x+7-5x^2");
		Polynom c = new Polynom("5x^3+7.1-5x^2");
		Polynom d = new Polynom("-5x-4x+5x^2+0");
		assertTrue(a.f(5)==-93);
		assertTrue(a.f(0)==7);
		assertTrue(a.f(-5)==-143);
		assertTrue(a.f(-1.5)==-11.75);
		assertTrue(b.f(5.3)==-128.14999999999998);
		assertTrue(c.f(0)==7.1);
		assertTrue(c.f(-5)==-742.9);
		assertTrue(d.f(-2.7)==60.75);
		
	}
	

}
