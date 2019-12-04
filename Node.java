package myMath;

public class Node {

	private function data;
	private Node left;
	private Node right;
	private Operation root;
	//לחשוב אם להוסיף משתנה ששומר את תוצאת הפונקציה, מסוג funcyion או duoble
	
//	public Node () {
//		this.data=null;
//		this.left=null;
//		this.right=null;
//		this.root=null;
//	}
	
	public Node(function data1) {
		this.data=null;
		this.left.data=data1;
		this.right.data=null;
		this.root=null;
	}
	public Node(function data1, function data2, Operation root) {
		this.data=null;
		this.left.data=data1;
		this.right.data=data2;
		this.root=root;
	}
	
	public Node(Node n, function data, Operation root) {
		this.data=null;
		this.left = n;
		this.right.data=data;
		this.root=root;
	}

	//public function getData() {}
	
	public void  setOperation(Operation operation) {
		this.root= operation;
	}
	
	
	public String toString() {
		return this.left.data+" "+this.root+" "+this.right.data;
	}
	

}
