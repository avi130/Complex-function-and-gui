package Ex1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	ArrayList<Monom> poly = new ArrayList<Monom>();
	/**
	 * Zero (empty polynom)
	 *
	 */
	
	public Polynom() {
		this.poly= new ArrayList<Monom>();
	}
	
	/**
	 * init a function type Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	@Override
	public function initFromString(String s) {
		function x= new Polynom(s);
		return x;
	}
	
	
	/** 
	 * this method checks whether the object is also instanceof Polynom_able and if so he checks if those two polynomials are equal to each other
	 * @param obj: Represents the object of type polynom we are trying to compare to the our polynomial
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Polynom_able ) {
			Polynom_able p1 =(Polynom_able)obj;
			boolean flag = false ;
			
			Iterator<Monom> polynom_new = p1.iteretor();
			Iterator<Monom> polynom_old = this.poly.iterator();
			while(polynom_new.hasNext()) {
				Monom i =polynom_new.next();
				flag=false;
				while(polynom_old.hasNext()&& flag==false) {
					Monom p =polynom_old.next();
					if(i.equals(p)) {
						flag=true;	
					}	
					if(flag == false)
						return false;
				}
			}
			if(polynom_old.hasNext())
				return false;
			return true;
		}
		else throw new ArithmeticException ("your object is not a Polynom");	
}


	/**
	 * init a Polynom from a String such as:
	 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		int i=0;
		char t= s.charAt(i);
		String Monom2="";
		if(t==' ') {
			i++;
			t= s.charAt(i);
		}
		while(i<s.length() ) {
			if( t=='-') {
				Monom2 += t;
				i++;
				t= s.charAt(i);
			}
			else if( t=='+') {
				i++;
				t= s.charAt(i);
			}
			while(t!='+' && t!='-' && i<s.length()) {
				if(t!=' ') {
					Monom2 += t;
					i++;
					if(i<s.length())
						t= s.charAt(i);
				}
				else
				{
					i++;
					t= s.charAt(i);
				}
			
			}
			Monom m = new Monom(Monom2);
			this.add(m);
			Monom2="";		
		}
	}
	
	/** 
	 * this method  calculates the polynomial 
	 * @param x: The variable of value with which we calculate the polynomial 
	 */
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		double ans= 0;
		Iterator<Monom> func= this.poly.iterator();
		while(func.hasNext()) {
			Monom m= (Monom)func.next();
			ans+= m.f(x);		
		}

		return ans;
	}

	
	/** 
	 * this method  performs an adding operation between 2 polynomials
	 * @param p1: Represents the polynomial we add to our old polynom
	 */
	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> polynom_new = p1.iteretor();
		while(polynom_new.hasNext()) {
		//	Monom i =polynom_new.next();
			this.add(polynom_new.next());
		}
	}

	
	/** 
	 * this method  performs an adding operation between  polynomial and monom
	 * @param m1: Represents the monom we add to the given polynomial
	 */
	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		Iterator<Monom> iter = iteretor();
		while(iter.hasNext() && !poly.isEmpty()) {
			Monom run = iter.next();
			if(run.get_power()==m1.get_power()) {
				run.add(new Monom(m1));
				return;
			}
		}
		poly.add(new Monom(m1));
		Monom_Comperator mc = new Monom_Comperator();
		this.poly.sort(mc);
		zeroCorrection();

	}
	public void zeroCorrection() {
		Iterator<Monom> iter = iteretor();
		while(iter.hasNext()) {
			Monom run = iter.next();
			if(run.get_coefficient()==0) {
				iter.remove();
			}
		}

		Monom_Comperator mc = new Monom_Comperator();
		this.poly.sort(mc);
	}

	
	/** 
	 * this method performs an subtract operation between 2 polynomials
	 * @param p1: Represents the polynomial we subtract from the given polynomial
	 */
	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> polynom_new = p1.iteretor();
	//	Monom minus= new Monom(-1,0);
		while(polynom_new.hasNext()) {
			Monom i =(Monom)polynom_new.next();
			Monom p= new Monom((i.get_coefficient())*-1, i.get_power());
			this.add(p);	
		}
	}

	
	/** 
	 * this method multiplies two polynomials
	 * @param p1: Represents the polynomial we multiply in the given polynomial
	 */
	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		Polynom x = new Polynom();
		Iterator<Monom> run2 = this.iteretor();
		while(run2.hasNext()) {
			Monom i = run2.next();	
			Iterator<Monom> run1 = p1.iteretor();
			while(run1.hasNext()) {
				Monom p =new Monom(run1.next());
				p.multipy(i);
				x.add(p);
			}
		}
		this.poly=x.poly;
	}

	
//	/** 
//	 * this method checks whether two polynomials are equal to each other
//	 * @param p1: Represents the polynomial we are trying to compare to the given polynomial
//	 */
//	public boolean equals(Polynom_able p1) {
//		// TODO Auto-generated method stub
//		boolean flag = false ;
//		Iterator<Monom> polynom_new = p1.iteretor();
//		Iterator<Monom> polynom_old = this.poly.iterator();
//		while(polynom_new.hasNext() ) {
//			Monom i =polynom_new.next();
//			flag=false;
//			while(polynom_old.hasNext()&& flag==false) {
//				Monom p =polynom_old.next();
//				if(i.equals(p)) {
//					flag=true;	
//				}	
//				if(flag == false)
//					return false;
//			}
//		}
//		return true;
//	}

	
	/** 
	 * this method checks if our Polynomial equal to 0
	 * 
	 */
	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		Iterator<Monom> zero = this.poly.iterator();
		boolean flag= true;
		while( zero.hasNext()) {
			Monom i = (Monom)zero.next();
			if(i.get_coefficient()!=0) {
				flag=false;
			}
		}		
		return flag;
	}

	
	/** 
	 * this method checks if there is a point function on the x axis between x0, x1 of exactly up to eps
	 * @param x0,x1: Represents Points on the graph
	 * @param eps : Represents the level of accuracy required to reach a solution **/
	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
			 if ( f(x0)*f(x1) > 0)
			 throw new RuntimeException("Both numbers above or below the X axis");
		
			 if (x1<x0)
			 throw new RuntimeException("Wrong input");

			 double mid = (x0+x1)/2;
		 if(Math.abs(f(x0))< eps)
			 return x0;
		 if(Math.abs(f(x1))< eps)
			 return x1;
		 if(Math.abs(f(mid))< eps)
			 return mid;
		 if(f(x0)>f(x1))
		 {
			if(f(mid)>0)
				x0 = mid;
			else
				x1=mid;
		 }
		 else
		 {
			 if(f(mid)<0)
					x0 = mid;
				else
					x1=mid; 
		 }
         return root(x0,x1,eps);	
	}

	
	/** 
	 * this method copies our polynomial
	 * 
	 */
	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		Iterator <Monom> old = this.iteretor();
		Polynom_able copy= new Polynom();
		while( old.hasNext()) {
			Monom i = old.next();
			copy.add(i);
		}
		return copy;
	}

	
	/** 
	 * this method makes a derivative of polynomial
	 * 
	 */
	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Polynom_able x= new Polynom();
		Iterator <Monom> old = this.iteretor();
		while(old.hasNext()) {
			Monom i = new Monom(old.next());	
			x.add(i.derivative());
		}	
		return x;
	}

	
	/** 
	 * this method finds an area between 2 points and above the X axis
	 * @param x0,x1: Represents Points on the graph
	 * @param eps : Represents the level of accuracy required to reach a solution
	  */
	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double s=x0;
		double e=x0+ eps;
		double areaa=0;
		while(e <=x1) {
			if(this.f(s)>0 && this.f(e)>0) {
				areaa= areaa+(eps*this.f(s));
			}
			s=e;
			e=e+eps;
		}
		return areaa;
	}

	
	/** 
	 * this method goes through every organ in the polynomial and with its help us know what is the organ
	 * 
	 */
	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		return	poly.iterator();

	}
	
	/** 
	 * this method multiplies polynomial and monom
	 * @param m1: Represents the monom we multiply with the given polynomial
	 */
	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub
		Polynom temp= new Polynom();
		Iterator<Monom> polynom= this.poly.iterator();
		while(polynom.hasNext()) {
			Monom x= polynom.next();
			x.multipy(m1);
			temp.poly.add(x);


		}
		this.poly=temp.poly;
		
	}

	/** 
	 * this method prints the polynomial
	 * 
	 */
	@Override
	public String toString() {
		String ans="";
		Iterator<Monom> polynom= this.poly.iterator();
		while(polynom.hasNext() ) {
			Monom m= (Monom)polynom.next();
			if(m.f(1)>=0) {
				if(ans=="")
					ans += m;
				else
					ans += "+"+ m;
			}		
			else
				ans +=""+ m;
		}
		
		return ans;
	}

}
