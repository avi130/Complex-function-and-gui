package myMath;

public class mytest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Polynom x= new Polynom("5x^2+6x"); Monom y= new Monom("6x");
		 * 
		 * x.add(y); System.out.println(x);
		 */
		  //System.out.println(x.add(y));
		
		
	//ComplexFunction x= new ComplexFunction(new Polynom("5x+1"), new Monom ("6x"), Operation.Comp);
	//System.out.println(x.f(2));
	ComplexFunction s= new ComplexFunction();
	s.initFromString("times(plus(7,2),3)");
	System.out.println(s.f(1));

}
}