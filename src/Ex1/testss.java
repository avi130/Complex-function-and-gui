package Ex1;

public class testss {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		function x=new ComplexFunction(new Polynom("5x"),new Polynom("3x"), Operation.Plus);
//		function z=new ComplexFunction(new Polynom("7"),new Polynom("5"), Operation.Times);
//		function a=new ComplexFunction(z,x, Operation.Max);
//		function aa=new ComplexFunction(a,x, Operation.Divid);
//		System.out.println(aa);
	//	function x=new ComplexFunction().initFromString("div(1.0x+1.0,mul(mul(1.0x+3.0,1.0x-2.0),1.0x-4.0))");
		//function xxx=new ComplexFunction().initFromString("divid(-1.0x^4 +2.4x^2 +3.1,plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0))");
		function xx=new ComplexFunction().initFromString("divid(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)");

		//		function xx=new ComplexFunction().initFromString("divid(5,plus(1,8x-2))");
		//function s=new ComplexFunction(new Polynom("1"),new Polynom("-2+8x"),Operation.Plus);
		//function v=new ComplexFunction(new Polynom("5"),s,Operation.Plus);
		//function vv=new ComplexFunction(s,new Polynom("5"),Operation.Plus);

	//System.out.println(xx.f(1));
		StdDraw.setCanvasSize(1000, 600);
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.setPenRadius();
		StdDraw.setFont();
		StdDraw.setXscale(-10,10);
		StdDraw.setYscale(-15,5);
		StdDraw.line(-10, 0, 10, 0);
		StdDraw.line(0, -15, 0, 5);
		for (int i = 0; i <10 ; i++) {
			StdDraw.line(xx.f(i), xx.f(i), xx.f(i+0.3), xx.f(i));
			
	}

	}}
