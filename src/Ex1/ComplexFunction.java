package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class ComplexFunction implements complex_function {

	private function left;
	private function right;
	private Operation root;

	/**
	* This method creates a new complex function where the left will be 0, the right will be null and the root will also be empty
	 */
	public ComplexFunction() {
		this.left = new Polynom("0");
		this.right = null;
		this.root = Operation.None;
	}
	
	public ComplexFunction(String data1) {
		
		this.left = initFromString(data1);
		this.root = Operation.None;
		this.right = null;

	}

	/**
	* This method creates a new complex function where the left will be the function we got, the right will be null and the root will also be empty.
	@param data 1 will be the function we got in the method
	*/
	public ComplexFunction(function data1) {
		this.left = data1;
		this.right = null;
		this.root = Operation.None;

	}

	/**
	* This method creates a new complex function where the left will be the function we received, the right will be the second function we received and the root will be the action we received.
	@param data 1, data2 will be the functions we got in the method
	@param root will be the operation we received
	*/
	public ComplexFunction(Operation root , function data1, function data2) {
		this.left = data1;
		this.right = data2;
		this.root = root;
	}
	
	/**
	* This method creates a new complex function where the left will be the function we received, the right will be the second function we received and the root will be the string we received.
	@param data 1, data2 will be the functions we got in the method
	@param rootstring will be the string we received and from which we will extract the operation
	*/
	public ComplexFunction(String rootString , function data1, function data2) {
		this.left = data1;
		this.right = data2;
		this.root = getOpFromString(rootString);
	}
	
	

	
	/**
	* This method obtains an X value and calculates the function
	@param x represents the value by which the function value is calculated
	*/
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		switch (root.toString() ) {
			case ("Plus"): {
				return this.left.f(x) + this.right.f(x);
			}
			case ("Divid"): {
				if(right.f(x) == 0)
					throw new ArithmeticException("/ by zero");
				return left.f(x) / right.f(x);
			}
			case ("Times"): {
				return this.left.f(x) * this.right.f(x);
			}
			case ("Max"): {
				return Math.max(this.left.f(x), this.right.f(x));
			}
			case ("Min"): {
				return Math.min(this.left.f(x), this.right.f(x));
			}
			case ("Comp"): {
				if (this.right != null)
					return this.left.f(this.right.f(x));
			}
			case ("None"): {
			}
			case ("Error"):

			default:
				throw new RuntimeException("Operation unknown");
		}
		
	}
	
	Operation newOperation = Operation.None;
	
	
	/**
	* This method reads the string and transforms it into a complex function or a complex function that contains several complex functions
	@param s represents the string that we turn into a function
	*/
	@Override
	public function initFromString(String s) {
		//Operation newOperation = Operation.None;
		// TODO Auto-generated method stub
		String stringOp = "";
		String l = "";
		String r = "";
		int index_start = 0;
		int index_end = 0;

		int open = 0;
		int close = 0;
		int psik = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				open++;
			} else if (s.charAt(i) == ')') {
				close++;
			} else if (s.charAt(i) == ',') {
				psik++;
			}
		}

		if (close == open && open == psik) { 
			if (close == 0 && open == 0 && psik == 0) {
				function z = new Polynom(s);
/*			
				if((this.left.equals(new Polynom("0"))||this.left==null) && this.right==null ) {
					return this.left=z;
				
				}
				else if(this.right==null ) {
					
					return this.right=z;
				}
				else if(this.right!=null && this.left!=null) {
					
					return this.right=z;	
				}	
	*/		
				return z;
				
			} else {
				open = 0;
				boolean flag = true;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) != '(') {
						if(open==0)stringOp += s.charAt(i);
						if (s.charAt(i) == ')') {
							open--;
						}
					}				
					else { 
						if (flag) {
							newOperation =getOpFromString(stringOp);
							flag = false;
							
							index_start = i + 1;
							index_end = s.length();
						}
						open++;
					}

					if (s.charAt(i) == ',' && open == 1) {
						l = s.substring(index_start, i);
						r = s.substring(i + 1, index_end-1);
						
						
					
						return new ComplexFunction( getOpFromString(stringOp), initFromString(l),initFromString(r) );
					}
				} // end for

			}

		}
		else
			throw new  ArithmeticException("eror,wrong input");


		return null;
	}

	
	/**
	* This method copies and creates a new complex function
	*/
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		function newCopy= new ComplexFunction( this.root ,this.left ,this.right);
		return newCopy;
	}

	
	/**
	*This Method can get 2 Complex Functions ans give them the plus Operation	
	@Param  f1 - the function that will get the plus Operation with our Complex Function
	*/
	@Override
	public void plus(function f1) {
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction( this.root ,this.left ,this.right);
			this.left = newleft;
			this.right = f1;
			this.root = Operation.Plus;
		} else {
			this.right = f1;
			this.root = Operation.Plus;
		}
	}

	
	/**
	*This Method can get 2 Complex Functions ans give them the mul Operation	
	@Param  f1 - the function that will get the mul Operation with our Complex Function
	*/
	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction( this.root ,this.left ,this.right);
			this.left = newleft;
			this.right = f1;
			this.root = Operation.Times;
		} else {
			this.right = f1;
			this.root = Operation.Times;
		}
	}

	
	/**
	*This Method can get 2 Complex Functions ans give them the Divid Operation	
	@Param  f1 - the function that will get the Divid Operation with our Complex Function
	*/
	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction( this.root ,this.left ,this.right);
			this.left = newleft;
			this.right = f1;
			this.root = Operation.Divid;
		} else {
			this.right = f1;
			this.root = Operation.Divid;
		}
	}

	
	/**
	*This Method can get 2 Complex Functions ans give them the Max Operation	
	@Param  f1 - the function that will get the MAx Operation with our Complex Function
	*/
	@Override
	public void max(function f1) {
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction( this.root ,this.left ,this.right);
			this.left = newleft;
			this.right = f1;
			this.root = Operation.Max;
		} else {
			this.right = f1;
			this.root = Operation.Max;
		}
	}

	
	/**
	*This Method can get 2 Complex Functions ans give them the Min Operation	
	@Param  f1 - the function that will get the Min Operation with our Complex Function
	*/
	@Override
	public void min(function f1) {
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction( this.root ,this.left ,this.right);
			this.left = newleft;
			this.right = f1;
			this.root = Operation.Min;
		} else {
			this.right = f1;
			this.root = Operation.Min;
		}

	}

	
	/**
	*This Method can get 2 Complex Functions ans give them the Comp Operation
	@Param f1 - the function that will get the Comp Operation with our Complex Function
	*/
	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction( this.root ,this.left ,this.right);
			this.left = newleft;
			this.right = f1;
			this.root = Operation.Comp;
		} else {
			this.right = f1;
			this.root = Operation.Comp;
		}
		
	}

	
	/**
	* This method returns a pointer to the left function
	*/
	@Override
	public function left() {
		// TODO Auto-generated method stub
		
		return this.left;
	}

	
	/**
	* This method returns a pointer to the right function
	*/
	@Override
	public function right() {
		// TODO Auto-generated method stub
		return this.right;
	}
	
	
	/**
	* This method returns a pointer to the operator

	*/
	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return this.root;
	}

	/**
	* This method gets a string and makes it an operator.
	@param x the string that Hita accepts and we make it an operator
	*/
	
	public Operation getOpFromString(String x) {
		String y=x.toLowerCase();
		switch (y) {
			case ("plus"): {
				return Operation.Plus;
			}
			case ("add"): {
				return Operation.Plus;
			}
			case ("divid"): {
				return Operation.Divid;
			}
			case ("div"): {
				return Operation.Divid;
			}
			case ("times"): {
				return Operation.Times;
			}
			case ("mul"): {
				return Operation.Times;
			}
			case ("max"): {
				return Operation.Max;
			}
			case ("min"): {
				return Operation.Min;
			}
			case ("comp"): {
				return Operation.Comp;
			}
			case ("none"): {
				return Operation.None;
			}
			default: return Operation.Error;
				
			}
		}
		
	
	
	/**
	* This method prints the function
	*/
	@Override
	public String toString() {
		String ans="";
		if(this.root!= Operation.None) {
			ans +=this.root.toString();
			
		ans+='(';
		if(this.left instanceof ComplexFunction || this.right instanceof ComplexFunction) {
			return ans+this.left.toString()+","+this.right.toString()+")";
		}
		else if(this.left instanceof Polynom ) {
			Polynom p1 =(Polynom)this.left;
			ans+= p1.toString();
			
		}
		else if(this.left instanceof Monom ) {
			Monom p1 =(Monom)this.left;
			ans+= p1.toString();
		
		}
		
		 if(this.right instanceof Polynom ) {
				ans+=',';
			Polynom p2 =(Polynom)this.right;
			ans+= p2.toString();	
		}
		else if(this.right instanceof Monom ) {
			ans+=',';
			Monom p1 =(Monom)this.right;
			ans+= p1.toString();
		}	
		ans+=')';
		}
		else if((this.root== Operation.None) && (this.left instanceof Polynom && this.right==null)) {
			
			if(this.left instanceof ComplexFunction || this.right instanceof ComplexFunction) {
				return ans+this.left.toString()+","+this.right.toString()+")";
			}
			else if(this.left instanceof Polynom ) {
				Polynom p1 =(Polynom)this.left;
				ans+= p1.toString();
				
			}
			else if(this.left instanceof Monom ) {
				Monom p1 =(Monom)this.left;
				ans+= p1.toString();
			
			}
			
			 if(this.right instanceof Polynom ) {
					ans+=',';
				Polynom p2 =(Polynom)this.right;
				ans+= p2.toString();	
			}
			else if(this.right instanceof Monom ) {
				ans+=',';
				Monom p1 =(Monom)this.right;
				ans+= p1.toString();
			}	
			
			
			
		}
		return ans;
	}
	
	
	
	/**
	* This method recives an object and checks if the objects are equal.(can only check from a certain range if they are even.
	*(from -100 to -1 and from 1 to 100 )
	@param obj The object we get and with which we make the comparison

	*/
	@Override
	public boolean equals(Object obj) {	
	 if(obj instanceof function ) {
		 function p1 =(function)obj;	
			for (double i = -100; i < 0; i=i+0.30) {
				
				if(Math.abs(this.f(i)-p1.f(i)) > Monom.EPSILON)
					return false;
			}
			for (double i =0.3; i < 100; i=i+0.30) {
				
				if(Math.abs(this.f(i)-p1.f(i)) > Monom.EPSILON)
					return false;
			}
	 }	
	return true;
	}
	

	
	
}
