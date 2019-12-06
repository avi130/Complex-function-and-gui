package myMath;

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

	public ComplexFunction(function data1, function data2, Operation root) {
		this.left = data1;
		this.right = data2;
		this.root = root;
	}

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		switch (root.toString()) {
			case ("Plus"): {
				return this.left.f(x) + this.right.f(x);
			}
			case ("Divid"): {
				if (this.right.f(x) == 0) {
					// לזרוק שגיאה שאי אפשר לחלק ב 0
				} else {
					return this.left.f(x) / this.right.f(x);
				}
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
			case ("Error"): {
				System.out.println("Eror");// לשנות את ההדפסה לזריקת שגיאה עם TRY ו CATCH
			}
		}
		return 0;
	}
	
	Operation newOperation = Operation.None;
	@Override
	
	public function initFromString(String s) {
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

		if (close == open && open == psik) { // בדיקה של תקינות קלט
			if (close == 0 && open == 0 && psik == 0) { // במקרה שקיבלנו פולינום בלבד
				function z = new Polynom(s);
			
				if(this.left.equals(new Polynom("0")) && this.right==null) {
					return this.left=z;
				
				}
				else if(this.right==null) {
					//ComplexFunction xx= new ComplexFunction(z);
					this.root = newOperation;
					return this.right=z;
				}
				else if(this.right!=null && this.left!=null) {
					ComplexFunction xxx= new ComplexFunction(this.left,this.right, newOperation) ;
					xxx.root=newOperation;
					this.left=xxx;
					return this.right=z;
					
				}	
			
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
							this.root=newOperation;
							flag = false;

							index_start = i + 1;
							index_end = s.length();
						}
						open++;
					}

					if (s.charAt(i) == ',' && open == 1) {
						l = s.substring(index_start, i);
						r = s.substring(i + 1, index_end-1);
						return new ComplexFunction(initFromString(l), initFromString(r), newOperation );
					}
				} // end for

			}

		}

//		else //קלט שגוי אפשרי לזרוק שגיאה

		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		function newCopy= new ComplexFunction(this.left, this.right, this.root);
		return newCopy;
	}

	@Override
	public void plus(function f1) {
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction(this.left, this.right, this.root);
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
			ComplexFunction newleft = new ComplexFunction(this.left, this.right, this.root);
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
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction(this.left, this.right, this.root);
			this.left = newleft;
			this.right = f1;
			this.root = Operation.Divid;
		} else {
			this.right = f1;
			this.root = Operation.Divid;
		}
	}

	@Override
	public void max(function f1) {
		if (this.right != null) {
			ComplexFunction newleft = new ComplexFunction(this.left, this.right, this.root);
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
			ComplexFunction newleft = new ComplexFunction(this.left, this.right, this.root);
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
			ComplexFunction newleft = new ComplexFunction(this.left, this.right, this.root);
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
			case ("divid"): {
				return Operation.Divid;
			}
			case ("times"): {
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
			case ("error"): { //default?
			}
		}
		return null;
	}
	
	public String toString() {
		
		return null;
	}
	
	
	

}
