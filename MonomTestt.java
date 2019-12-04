package myMath;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class MonomTestt {

	@Test
	void isZeroTest() {
		Monom x= new Monom(0,5);
		Monom y= new Monom(-5,0);
		Monom z= new Monom(-1,0);
		boolean resultx =x.isZero();
		boolean resulty =y.isZero();
		boolean resultz =z.isZero();
		assertTrue(resultx);
		assertFalse(resulty);
		assertFalse(resultz);
	
	}
	@Test
	void isMonomFromStringTest() {
		Monom a= new Monom("5x");
		Monom expected_a=new Monom(5,1);
		Monom b=new Monom("-5x");
		Monom expected_b=new Monom(-5,1);
		Monom c=new Monom("x^2");
		Monom expected_c=new Monom(1,2);
		Monom d=new Monom("0.5x^4");
		Monom expected_d=new Monom(0.5,4);
		assertTrue( a.equals(expected_a));
		assertTrue( b.equals(expected_b));
		assertTrue( c.equals(expected_c));
		assertTrue( d.equals(expected_d));		
	}
	
	@Test
	void addTest() {
		Monom monoms = new Monom(0,0);
		monoms.add(new Monom(5,0));
		Monom ans=new Monom(5,0);
		assertTrue(monoms.equals(ans));
		
		Monom monoms1=new Monom(-1,1);
		monoms1.add(new Monom(6.3,1));
		Monom ans1=new Monom(5.3,1);
		assertTrue(monoms1.equals(ans1));
		
		Monom monoms2=new Monom(5,4);
		monoms2.add(new Monom(6.3,4));
		Monom ans2=new Monom(11.3,4);
		assertTrue(monoms2.equals(ans2));		
	}
	@Test
	void multipyTest() {
		Monom monoms1=new Monom(-1,1);
		Monom monoms2=new Monom(2,4);
		monoms1.multipy(monoms2);
		Monom ans=new Monom(-2,5);
		assertTrue(monoms1.equals(ans));
		
		Monom monoms3=new Monom(0,5);
		Monom monoms4=new Monom(5,5);
		monoms3.multipy(monoms4);
		Monom ans2=new Monom(0,10);
		assertTrue(monoms3.equals(ans2));
		
		Monom monoms5=new Monom(-2,5);
		Monom monoms6=new Monom(7.5,4);
		monoms5.multipy(monoms6);
		Monom ans3=new Monom(-15,9);
		assertTrue(monoms5.equals(ans3));		
	}
	@Test
	void toStringTest(){
		Monom monoms1=new Monom(-1,1);
		Monom monoms2=new Monom(2,4);
		Monom monoms3=new Monom(-2,0);
		String a=  "-1.0x";
		String b= "2.0x^4";
		String c= "-2.0";
		String d= "-2";
		assertTrue(monoms1.toString().equals(a));
		assertTrue(monoms2.toString().equals(b));
		assertTrue(monoms3.toString().equals(c));
		assertFalse(monoms3.toString().equals(d));
	}
	@Test
	void equalsTest() {
		Monom monoms1=new Monom(-1,1);
		Monom monoms2=new Monom(2,4);
		Monom monoms3 = new Monom(0,0);
		Monom monoms4 = new Monom(0,5);
		assertFalse(monoms1.equals(monoms2));
		assertTrue(monoms1.equals(monoms1));
		assertTrue(monoms3.equals(monoms4));
	}
		
}
	

