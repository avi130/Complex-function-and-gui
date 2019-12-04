package myMath;
import java.util.ArrayList;
/**
 * This class represents a simple (naive) tester for the Monom class, 
 * Note: <br>
 * (i) The class is NOT a JUNIT - (i.e., educational reasons) - should be changed to a proper JUnit in Ex1. <br>
 * (ii) This tester should be extend in order to test ALL the methods and functionality of the Monom class.  <br>
 * (iii) Expected output:  <br>
 * *****  Test1:  *****  <br>
0) 2.0    		isZero: false	 f(0) = 2.0  <br>
1) -1.0x    	isZero: false	 f(1) = -1.0  <br>
2) -3.2x^2    	isZero: false	 f(2) = -12.8  <br>
3) 0    		isZero: true	 f(3) = 0.0  <br>
*****  Test2:  *****   <br>
0) 0    		isZero: true  	eq: true  <br>
1) -1.0    		isZero: false  	eq: true  <br>
2) -1.3x    	isZero: false  	eq: true  <br>
3) -2.2x^2    	isZero: false  	eq: true  <br>
 */
public class MonomTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
	}
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2","-x","-3.2x^2","0"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
		}
	}
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,1));
		monoms.add(new Monom(-1.3,1));
		monoms.add(new Monom(-2.2,2));
		System.out.println(monoms);
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();			
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"  \teq: "+e);
		}
	}
	private static void test3() {
		int count=0;
		System.out.println("*****  Test3:(bad case test)  *****");
		String[] monoms = {"2x.","-tx","-3..2x^-2","5%x  "};
		for(int i=0;i<monoms.length;i++) {
			try {
			Monom m = new Monom(monoms[i]);
			String s = m.toString();
			m = new Monom(s);
			System.out.println(m);
			}
			catch(Exception e){
				count++;
				System.out.println("you insert wrong monom: "+monoms[i]);		
			}
			
		}
		System.out.println();
		System.out.println("there are: "+count+" wrong Momons");
	}
	private static void test4() {
		System.out.println("*****  Test4:  *****");
		String q="5.51x";
		String w="5";
		Monom d = new Monom(q);	
		Monom s = new Monom(w);
		System.out.println("q= "+q);
		System.out.println("w= "+w);
		System.out.println("Are they equal? "+d.equals(s));
		String a="3.00000001x";
		String b="3x";
		Monom x = new Monom(a);	
		Monom y = new Monom(b);
		System.out.println("x= "+x);
		System.out.println("y= "+y);
		System.out.println("Are they equal? "+x.equals(y));
		x.add(y);
		System.out.println("x+y= "+x);
		
		String c="7x^2";
		Monom z = new Monom(c);	
		System.out.println("z= "+z);
		System.out.println("when x=2 z="+z.f(2));
		System.out.println("when x=0 z="+z.f(0));
		System.out.println("when x=4 z="+z.f(4));
	
	
	}
	private static void test5() {
		System.out.println("*****  Test5:BAD TEST !!  *****");	
		String a="3..x";
		String b="2x^x";
		String c=" 0.5x^7x";
		String d=" 3tx";
		try {
		Monom z = new Monom(a);	
		System.out.println(z);
		}
		catch(Exception e){
				System.out.println("you insert wrong monom: "+a);		
		}
		try {
			Monom x = new Monom(b);
			System.out.println(x);
			}
			catch(Exception e){
				System.out.println("you insert wrong monom: "+b);		
			}
		try {
			Monom v = new Monom(c);	
			System.out.println(v);
			}
			catch(Exception e){
				System.out.println("you insert wrong monom: "+c);		
			}
		try {
			Monom n = new Monom(d);	
			System.out.println(n);
			}
			catch(Exception n){
				System.out.println("you insert wrong monom: "+d);		
			}
		
		
		
		
		

		
		
	
	
	}
}

