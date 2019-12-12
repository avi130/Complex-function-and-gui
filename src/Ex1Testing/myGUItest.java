package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Operation;
import Ex1.Range;
import Ex1.function;

class myGUItest {

	@Test
	void testDraw() {
	function cf = new ComplexFunction().initFromString("max(div(5x,8),8x^2)");
	function cf2 = new ComplexFunction().initFromString("div(mul(x,9x),4x)");
	function cf3 = new ComplexFunction().initFromString("plus(mul(2x,9),4.5x)");
	function cf4 =new ComplexFunction().initFromString("Divid(Plus(3.1+2.4x^2-1.0x^4,5.0-1.2999999999999998x+0.1x^5),3.1+2.4x^2-1.0x^4)");
	function cf5 = new ComplexFunction("times", cf,cf2);

	Functions_GUI fll = new Functions_GUI();
	fll.add(cf);
	fll.add(cf2);
	fll.add(cf3);
	fll.add(cf4);
	fll.add(cf5);
	try {
		fll.saveToFile("avi.txt");
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	String str="avi.txt";
	Functions_GUI dt= new Functions_GUI();
	try {
		dt.initFromFile(str);
	} catch (IOException e) {
		e.printStackTrace();
	}
	dt.drawFunctions(1000, 600, new Range(-20, 20),new Range(-5,15), 200);
	
	
	
	}

}
