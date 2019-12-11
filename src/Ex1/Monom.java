package Ex1;

import java.util.Collections;
import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 * 
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	
	public Monom(double a, int b){    
		this.set_coefficient(a);
		this.set_power(b);	
	}
	
	// ***********************************************************************
	/** 
	 * this method copies our Monom
	 * 
	 */
	public function copy() {
		function x= new Monom(this._coefficient, this._power);
		return x;
	}
	
	/** 
	 * init a function of type Monom from a String(without multiplication marks between numbers) such as:
	 * { "x", "5x", "7.2x^3", "4.5"}
	 * If the user entered incorrect input, the function prints the same input and counts it as zero
	 * @param s: is a string represents a Monom
	 */
	public function initFromString(String s) {
		function x =new Monom(s);
		return x;
	}
	/** 
	 * this method checks whether the object is also instanceof Monom and if so he checks if those two Monoms are equal to each other
	 * @param obj: Represents the object of type Monom we are trying to compare to the our Monom
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Monom || obj==new Monom("0")) {
			Monom m= (Monom)obj;
			
			if (( m._coefficient == get_coefficient())&&(get_coefficient()==0))
				return true;
			double dc=m._coefficient - get_coefficient();
			 if ((Math.abs(dc)<=EPSILON) && (m._power ==get_power()) )
					return true;
	
			
			else return false;
			}
		else throw new ArithmeticException ("your object is not a Monom");	
	}
	// ************************************************************************
	/** 
	 * this method copies an existing monom
	 * @param ot : existing monom
	 */
	public Monom(Monom ot) {    
		this(ot.get_coefficient(), ot.get_power());
	}
	/** 
	 * this method tell us what the coefficient of the monom
	 * 
	 */
	public double get_coefficient() { 
		return this._coefficient;
	}
	/** 
	 * this method tell us what the power of the monom
	 * 
	 */
	public int get_power() { 
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {  
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/** 
	 * this method  calculates the monom
	 * @param x: The variable of value with which we calculate the monom
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	/** 
	 * this method checks if there is a coefficient equal to 0
	 * 
	 */
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	/** 
	 * init a Monom from a String(without multiplication marks between numbers) such as:
	 * { "x", "5x", "7.2x^3", "4.5"}
	 * If the user entered incorrect input, the function prints the same input and counts it as zero
	 * @param s: is a string represents a Monom
	 */
	public Monom(String s) { 
		int _power=0;
		int i=0;
		String base="";
		char t= s.charAt(i);
		boolean negative=false;
		
		if (t=='-') {
			i++;
			negative=true;
			t= s.charAt(i);
		}
		if (t=='x' ||t=='X') {
			base="1";
			double _coefficient= Double.parseDouble(base);
			if (negative==true) {
				_coefficient=_coefficient*-1;
				}
			this._coefficient=_coefficient;
			}
		else {
		while((t<='9' && t>='0' && i<s.length()) || (i<s.length() && t=='.') ){ 
			base +=s.charAt(i);
			if(s.length()-i-1 > 0) {
				t=s.charAt(i+1);
			}
			i++;
		}
		}
		try {
		if ((t-48<0 || t-57>0 )&& t!='x' && t!='X' ) {	
				throw new ArithmeticException ("you insert wrong coefficient");	
			}
		}
		catch(Exception e){
				throw new ArithmeticException ("you insert wrong coefficient");	
				//System.out.println("you insert wrong Monom "+"'"+s+"'"+ " The monom is not considered in the calculation");
				//base ="0";
				
			}
	
		try {
		double _coefficient= Double.parseDouble(base);
		if (negative==true) {
			_coefficient=_coefficient*-1;
			}
		this._coefficient=_coefficient;
		}
			catch(Exception e){
				throw new ArithmeticException ("you insert wrong coefficient");	
				//	System.out.println("you insert wrong Monom "+"'"+s+"'"+ " The monom is not considered in the calculation");		}
		}
		String heska="";
			if (t =='x' || t=='X'){
			if (s.length()-i-1 > 0) {
				i++;	
				t = s.charAt(i);
				if (t =='^'){
					if(s.length()-i > 0) {
					i++;
					t = s.charAt(i);
						while(i < s.length()) {
							try {
							if (t=='-')
								throw new ArithmeticException ("you cant add negative power");
							if (t=='.' || (t-48<0 || t-57>0 ))
								throw new ArithmeticException ("you cant add demical number as power");
							heska += s.charAt(i);
							if (s.length()-i-1 > 0) {
								i++;
								t=s.charAt(i);					
							}
							else i=s.length();
						}
							catch(Exception e){
								//System.out.println("you insert wrong Power "+"'"+s+"'"+ " this monom is not considered in the calculation");
								this._coefficient=0;
								heska="0";
								i++;
								t = s.charAt(i);
								throw new ArithmeticException ("you insert wrong Power");	
							}
						}
					}
				}
				else {
						throw new ArithmeticException ("");
					
				}
			}	
			else heska="1";	
			
			}
			else {
				heska="0";
			}
		
			_power= Integer.parseInt(heska);
			this._power=_power;
		}
		
	/** 
	 * this method checks whether two monoms are equal to each other
	 * @param m: Represents the monom we are trying to compare to the given monom
	 */
//	public boolean equals(Monom m) {
//		if (( m._coefficient == get_coefficient())&&(get_coefficient()==0))
//			return true;
//		double dc=m._coefficient - get_coefficient();
//		 if ((Math.abs(dc)<=EPSILON) && (m._power ==get_power()) )
//				return true;
//
//		
//		else return false;
//	}
	
	/** 
	 * this method  performs an adding operation between 2 monoms
	 * @param m: Represents the monom we add to the given monom
	 */
	public void add(Monom m) {
		
		if(m._power== this._power) {
		 	this.set_coefficient(m._coefficient + this._coefficient);
		 	this._power=m._power;
		}
		else 
			throw new ArithmeticException ("you cant add these two Monoms");
		
	}
	/** 
	 * this method multiplies two monoms
	 * @param d: Represents the monom we multiply in the given monom
	 */
	public void multipy(Monom d) {
		this._coefficient= d._coefficient* get_coefficient();
		this._power= d._power+ get_power();
		
		}
	/** 
	 * this method prints the monom
	 * 
	 */
	public String toString() {
		String ans = "";
		if (get_coefficient()==0)
			return "0";
		else
			ans += get_coefficient();
		if (get_power() ==0 )
			return ans;
		else if (get_power() ==1 ) {
			ans +="x";
			return ans;
			}
		if (get_power() >1 )
			ans +="x"+"^"+get_power();
		return ans;
	}
	// you may (always) add other methods.

	//****************** Private Methods and Data *****************
	

	private void set_coefficient(double a){
		
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	
}
