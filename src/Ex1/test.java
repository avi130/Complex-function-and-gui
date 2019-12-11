package Ex1;

import java.io.IOException;
import java.util.LinkedList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		function x=new ComplexFunction().initFromString("times(plus(5x+8,3x^2+1),3)");
//		function y=new ComplexFunction().initFromString("times(5x,8)");
//		function z=new ComplexFunction().initFromString("plus(5x^2,9x)");
//		
		function x=new ComplexFunction().initFromString("plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)");
		function y=new ComplexFunction().initFromString("plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)");
		function z=new ComplexFunction().initFromString("div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");
		function xx=new ComplexFunction().initFromString("-1.0x^4 +2.4x^2 +3.1");
		function zz=new ComplexFunction().initFromString("+0.1x^5 -1.2999999999999998x +5.0");
		function yy=new ComplexFunction().initFromString("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
		function xxx=new ComplexFunction().initFromString("min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
//		function xxxx=new ComplexFunction().initFromString("min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
//		function p= new ComplexFunction().initFromString("Times(Plus(6.0x^5,9.0x^6),9.0x^6)");
//		System.out.println(p.f(4));
//		System.out.println("v");
//		LinkedList<function> list= new LinkedList<function>();
//		list.add(x);
//		list.add(y);
//		list.add(z);
//		String s="Divid(Times(3.1+2.4x^2-1.0x^4,5.0-1.2999999999999998x+0.1x^5),3.1+2.4x^2-1.0x^4)";
//		function cf= new ComplexFunction().initFromString("Times(3.1+2.4x^2-1.0x^4,5.0-1.2999999999999998x+0.1x^5)");
//		ComplexFunction cf4 = new ComplexFunction("div", new Monom("x"),new Polynom("24.0-50.0x+35.0x^2-10.0x^3+1.0x^4"));
//		function cf5 = cf4.initFromString(s);
//		System.out.println(cf5);
//		System.out.println(cf);
//		if(cf.equals(cf5)) 
//			System.out.println("gooood!!");
//		
//		
		
		
	/*	
String str="function_file.txt";
Functions_GUI dt= new Functions_GUI();
try {
	dt.initFromFile(str);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
dt.drawFunctions(1000, 600, new Range(-10, 10),new Range(-15,5), 200);
		System.out.println(dt.list);
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
//		
//		
//		
		Functions_GUI fl = new Functions_GUI();
		fl.add(x);
		fl.add(y);
		fl.add(z);
		fl.add(xx);
		fl.add(yy);
		fl.add(zz);
		fl.add(xxx);

System.out.println(y.f(3.9));
		try {
			fl.saveToFile("avi katlani.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String str="avi katlani.txt";
		Functions_GUI dt= new Functions_GUI();
		try {
			dt.initFromFile(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dt.drawFunctions(1000, 600, new Range(-10, 10),new Range(-5,15), 200);
				System.out.println(dt.list);
		
		
		
	}

}
