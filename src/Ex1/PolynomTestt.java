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
	@Test
	void testNonEmptyPolynom() {
		Polynom newPolynom1 = new Polynom("-5x^8+4x^1+6x+1");
		assertNotNull(newPolynom1);
		Polynom newPolynom2 = new Polynom();
		String[] Monoms = {"7x^2","x^5","3.5x^2","2x","x^2","-2x","2"};
		for (int i = 0; i < Monoms.length; i++) {
			try {
			Monom m = new Monom(Monoms[i]);
			newPolynom2.add(m);
			} catch (Exception e) {
				System.out.println("Invalid monom has been inserted! Refer it as 0");
			}
		}
		
		assertNotNull(newPolynom2);
		assertFalse(newPolynom2.equals(newPolynom1));
	}
	
	@Test
	void testAddPolynomMonom() {
		Polynom newPolynom1 = new Polynom("2x^3+2.5");
		Monom newMonom = new Monom("4x^3");
		Polynom Result1 = new Polynom("6x^3+2.5");
		
		newPolynom1.add(newMonom);
		assertEquals(true, newPolynom1.equals(Result1));
		
		Polynom newPolynom2 = new Polynom("5x+3-5x");
		Monom newMonom2 = new Monom("3");
		Polynom Result2 = new Polynom("6x^3+2.5");
		
		newPolynom2.add(newMonom2);
		assertEquals(true, newPolynom1.equals(Result2));
	}
	
	@Test
	void testMultiplyPolynoms() {
		Polynom newPolynom1 = new Polynom("2x^5+11x^2+8");
		Polynom newPolynom2 = new Polynom("3x^5+1");
		Polynom Result1 = new Polynom("6x^10+2x^5+33x^7+11x^2+8+24x^5");
		
		newPolynom1.multiply(newPolynom2);
		assertEquals(true, newPolynom1.equals(Result1));
		
		Polynom newPolynom3 = new Polynom("x+1");
		Polynom newPolynom4 = new Polynom("x+2");
		Polynom Result2 = new Polynom("3x+2");
		
		newPolynom3.multiply(newPolynom4);
		
		assertEquals(false, newPolynom3.equals(Result2));
	}
	@Test
	void testArea() {
		Polynom newPolynom = new Polynom("x^3");
		newPolynom.area(0, 2, Monom.EPSILON);
		assertEquals(false, newPolynom.equals(new Polynom("9999996006061598")));
	}
	
	@Test
	void testDerivative() {
		Polynom newPolynom1 = new Polynom("2x^3+5x^2+6x+1");
		Polynom newPolynom2 = new Polynom("6x^2+10x+6");
		
		assertTrue(newPolynom1.derivative().equals(newPolynom2));
		assertFalse(newPolynom1.derivative() == newPolynom2.derivative().derivative());
	}
	
	@Test
	void testRoot() {
		Polynom newPolynom = new Polynom("x^5");
		newPolynom.root(-3.14, 2.72, Monom.EPSILON);
		assertEquals(false, newPolynom.equals(new Polynom("5x")));
	}
	
	@Test
	void testCopy() {
		Polynom newPolynom1 = new Polynom("5x+8+6x^3-5-7x");
		Polynom_able newPolynom2 = newPolynom1.copy();
		
		assertEquals(true, newPolynom1.equals(newPolynom2));
	}
	@Test
	void testEquals() {
		Polynom newPolynom1 = new Polynom("2x^3");
		Polynom newPolynom2 = new Polynom("5x^3");
		assertFalse(newPolynom1.equals(newPolynom2));
		
		Polynom newPolynom3 = new Polynom("5x+6+7x-1");
		Polynom newPolynom4 = new Polynom("12x+5");
		assertTrue(newPolynom3.equals(newPolynom4));
		
		Polynom newPolynom5 = new Polynom("5x-5x+1");
		Polynom newPolynom6 = new Polynom("1");
		assertTrue(newPolynom5.equals(newPolynom6));
	}
	
	@Test
	void testIsZero() {
		Polynom newPolynom1 = new Polynom("0-0x^7+0x+0x^2");
		assertEquals(true, newPolynom1.isZero());
		
		Polynom newPolynom2 = new Polynom("0-0x^7+1x+0x^2");
		assertEquals(false, newPolynom2.isZero());
		
	}
	


}
