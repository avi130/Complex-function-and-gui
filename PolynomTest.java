package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
	
	}
	public static void test1() {
		System.out.println("Test 1");
		System.out.println();
		Polynom p1 = new Polynom();
		String[] monoms = {"x^2","-x","x^2","0.5x^2","0.5x^7","32x^0","0.5"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		System.out.println(p1);
		p1.add(new Monom("x^4"));
		System.out.println(p1);
		
		
		double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
		System.out.println(aa);
	
	}
	public static void test2() {
		System.out.println("Test 2, bad case test!");
		System.out.println();
		Polynom p1 = new Polynom();
		int count=0;
		String[] monoms = {"-px^.2", "0..5x^2", "^2", "+5x", "5x^^2", "2^-6"};
		for(int i=0;i<monoms.length;i++) {
			try {
				Monom m = new Monom(monoms[i]);
				p1.add(m);
				}
				catch(Exception e){
					count++;
					System.out.println("you added wrong monom:  "+monoms[i]);		
				}
		}
		
		System.out.println(p1); 
		System.out.println("the number of wrong Monoms is: "+count);
	}
	public static void test3() {
		System.out.println();
		System.out.println("Test 3");
		System.out.println();
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"5x^3", "-1.7x^2","-3.2x^2","4","6.5x^2"};
		String[] monoms2 = {"5x", "7x","3x^2","-3","-1.5x^3"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		System.out.println("p1 with x=6: "+p1.f(6));
		System.out.println("p2 with x=4.5: "+p2.f(4.5));
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		System.out.println(s1);
		Polynom_able pp1 =new Polynom(s1);
		System.out.println("from string: "+pp1);
		p1.multiply(p2);
		System.out.println(p1);
		
		
		try {
		System.out.println("the root of p1 is:" + p1.root(-2, 0.5, 0.0001));
		}
		catch(Exception e){
			System.out.println("Wrong input !!  x0>x1 -OR- Both numbers above or below the X axis");
		}
		
		}

}
