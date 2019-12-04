package myMath;

import java.util.ArrayList;
import java.util.Iterator;

public class ComplexFunction implements complex_function {
	
	private function left;
	private function right;
	private Operation root;


	public ComplexFunction() {
		this.left= new Polynom("0");
		this.right=null;
		this.root=Operation.None;
	}
	
	public ComplexFunction(function data1) {
		this.left=data1;
		this.right=null;
		this.root=Operation.None;
		
	}
	
	public ComplexFunction(function data1, function data2, Operation root) {
		this.left=data1;
		this.right=data2;
		this.root=root;
	}


	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		switch (root.toString()) {
		case("Plus"):{
			return this.left.f(x)+this.right.f(x);
		}
		case("Divid"):{
			if(this.right.f(x)==0) {
				//לזרוק שגיאה שאי אפשר לחלק ב 0
			}
			else {				
			return this.left.f(x)/this.right.f(x);
			}
		}
		case("Times"):{
			return this.left.f(x)*this.right.f(x);
		}
		case("Max"):{
			return Math.max(this.left.f(x), this.right.f(x));
		}
		case("Min"):{
			return Math.min(this.left.f(x), this.right.f(x));
		}
		case("Comp"):{
			if(this.right!=null)
				return this.left.f(this.right.f(x));
		}
		case("None"):{
		}
		case("Error"):{
			System.out.println("Eror");//  לשנות את ההדפסה לזריקת שגיאה עם TRY ו CATCH
		}
	}
		return 0;
}

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < s.length(); i++) {
			
		}
		
		
		return null;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void plus(function f1) {
		if(this.right != null) {
			ComplexFunction newleft= new ComplexFunction(this.left, this.right, this.root);
			this.left = newleft;
			this.right = f1;
			this.root =Operation.Plus;
		}
		else {
			this.right=f1;
			this.root= Operation.Plus;
		}
	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		if(this.right != null) {
			ComplexFunction newleft= new ComplexFunction(this.left, this.right, this.root);
			this.left = newleft;
			this.right = f1;
			this.root =Operation.Times;
		}
		else {
			this.right=f1;
			this.root= Operation.Times;
		}
	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		if(this.right != null) {
			ComplexFunction newleft= new ComplexFunction(this.left, this.right, this.root);
			this.left = newleft;
			this.right = f1;
			this.root =Operation.Divid;
		}
		else {
			this.right=f1;
			this.root= Operation.Divid;
		}
	}

	@Override
	public void max(function f1) {
		if(this.right != null) {
			ComplexFunction newleft= new ComplexFunction(this.left, this.right, this.root);
			this.left = newleft;
			this.right = f1;
			this.root =Operation.Max;
		}
		else {
			this.right=f1;
			this.root= Operation.Max;
		}
	}

	@Override
	public void min(function f1) {
		if(this.right != null) {
			ComplexFunction newleft= new ComplexFunction(this.left, this.right, this.root);
			this.left = newleft;
			this.right = f1;
			this.root =Operation.Min;
		}
		else {
			this.right=f1;
			this.root= Operation.Min;
		}
		
	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		ComplexFunction newleft= new ComplexFunction(this.left, this.right, this.root);
		
	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		switch (key) {
		case value:
			
			break;

		default:
			break;
		}
		return null;
	}


	}
