package Ex1;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class ComplexFunction implements complex_function {

	private function left;
	private function right;
	private Operation root;

	
	public ComplexFunction() {
		this.left = new Polynom("0");
		this.right = null;
		this.root = Operation.None;
	}

	public ComplexFunction(function data1) {
		this.left = data1;
		this.right = null;
		this.root = Operation.None;

	}

	public ComplexFunction(Operation root , function data1, function data2) {
		this.left = data1;
		this.right = data2;
		this.root = root;
	}
	
	public ComplexFunction(String rootString , function data1, function data2) {
		this.left = data1;
		this.right = data2;
		this.root = getOpFromString(rootString);
	}
	
	

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		switch (root.toString() ) {
			case ("Plus"): {
				return this.left.f(x) + this.right.f(x);
			}
			case ("Divid"): {
				if(right.f(x) != 0 )
                    return left.f(x) / right.f(x);
                else
                    throw new ArithmeticException("Can't Divide with zero");
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
			default: throw new ArithmeticException("Operation do not exist");
		}
		
	}
	
	Operation newOperation = Operation.None;
	@Override
	
	public function initFromString(String s) {
		//Operation newOperation = Operation.None;
		// TODO Auto-generated method stub
		String stringOp = "";
		String l = "";
		String r = "";
		int index_start = 0;
		int index_end = 0;
		function hadash= new ComplexFunction() ;

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

		if (close == open && open == psik) { // בדיקה של תקינות קלט
			if (close == 0 && open == 0 && psik == 0) { // במקרה שקיבלנו פולינום בלבד
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
	*/		return z;
				
			} else {
				open = 0;
				boolean flag = true;
				for (int i = 0; i < s.length(); i++) { // while?
					if (s.charAt(i) != '(') {
						if(open==0)stringOp += s.charAt(i);
						if (s.charAt(i) == ')') {
							open--;
						}
					}
					// else if(s.charAt(i)=='(' && stringOp.length()>2 && stringOp.length()<6) {
					// //אפשר להעביר לפונקציית עזר
					else { // s.charAt(i)=='('
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

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		function newCopy= new ComplexFunction( this.root ,this.left ,this.right);
		return newCopy;
	}

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

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
//		if (this.right != null) {
//			ComplexFunction newleft = new ComplexFunction( this.root ,this.left ,this.right);
//			this.left = newleft;
//			this.right = f1;
//			this.root = Operation.Divid;
//		} else {
//			this.right = f1;
//			this.root = Operation.Divid;
//		}
		if(right!=null){
            ComplexFunction prevComplex = new ComplexFunction(getOp(),left(),right());
            this.left =  prevComplex;
        }
        this.right = new ComplexFunction(f1);
        this.root =Operation.Divid;
	}

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

	@Override
	public function left() {
		// TODO Auto-generated method stub
		
		return this.left;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return this.right;
	}

	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return this.root;
	}

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
		
	
	
	
	public String toString() {
		String ans="";
		ans +=this.root.toString();
		ans+='(';
		if(this.left instanceof ComplexFunction || this.right instanceof ComplexFunction) {
			return ans+this.left.toString()+","+this.right.toString()+")";
		}
		else if(this.left instanceof Polynom ) {
			Polynom p1 =(Polynom)this.left;
			ans+= p1.toString();
			ans+=',';
		}
		else if(this.left instanceof Monom ) {
			Monom p1 =(Monom)this.left;
			ans+= p1.toString();
			ans+=',';
		}
		
		if(this.right instanceof ComplexFunction ) {
			return ans+this.left.toString()+","+this.right.toString()+")";
		}
		 if(this.right instanceof Polynom ) {
			Polynom p2 =(Polynom)this.right;
			ans+= p2.toString();	
		}
		else if(this.right instanceof Monom ) {
			Monom p1 =(Monom)this.right;
			ans+= p1.toString();
		}	
		ans+=')';
		
		return ans;
	}
	
	
	
	
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
