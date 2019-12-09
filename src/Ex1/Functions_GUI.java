package Ex1;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;



public class Functions_GUI implements functions   {
	
	
 LinkedList<function> list= new LinkedList<function>();
 
	public Functions_GUI() {
		list = new LinkedList<function>();
	}

	public static Color[] Colors = {Color.blue, Color.cyan, Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK}; 
 
	@Override
	public boolean add(function arg0) {
		// TODO Auto-generated method stub
		
		return list.add(arg0);
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		// TODO Auto-generated method stub
		return list.addAll(arg0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		list.clear();
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return list.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return list.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return list.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return list.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return list.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return list.retainAll(c);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return list.toArray(a);
	}

	
	
	
	
	@Override
	public void initFromFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
		
		BufferedReader bf= new BufferedReader(new FileReader(file));
		String hadash="";
		while ((hadash=bf.readLine()) !=null) {
			function t= new ComplexFunction().initFromString(hadash);
			list.add(t);
			
		}
		bf.close();
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		PrintWriter print=new PrintWriter(file);
		for (int i = 0; i < list.size(); i++) {
			print.println(list.get(i).toString());
		}
		print.close();
			
		}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(width, height);
		StdDraw.setPenColor(StdDraw.BOOK_RED);
		StdDraw.setPenRadius();
		StdDraw.setFont();
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		int size= this.list.size();
		double x=0;
		for (int i=0; i <list.size() ;i++) {
			StdDraw.line(list.get(i).f(x), 1, list.get(i+1).f(x+0.035), 1);
			x=x+0.035;
			
		}
		
		
		
	}

	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		
	}

}
