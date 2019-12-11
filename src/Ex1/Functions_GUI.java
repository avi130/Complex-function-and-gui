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

import com.google.gson.Gson;

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
//	public void initFromFile(String file) throws IOException {
//		// TODO Auto-generated method stub
//		
//		
//		BufferedReader bf= new BufferedReader(new FileReader(file));
//		String hadash="";
//		while ((hadash=bf.readLine()) !=null) {
//			function t= new ComplexFunction().initFromString(hadash);
//			list.add(t);
//			
//		}
//		bf.close();
//		
//	}
	public void initFromFile(String file) throws IOException {
		String line = "";
		
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));
        	
            while ((line = br.readLine()) != null) 
            {
                String info = line.toString();
                function f = new ComplexFunction().initFromString(info);
               // f.initFromString(info);
                add(f);
            }
            br.close();

        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("could not read file");
        }
		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub		
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));
			
			StringBuilder sb = new StringBuilder();
			int i=0;
			while (this.list.size()>i) {
				String s=this.list.get(i).toString();
				sb.append(s);
				sb.append("\n");
				i++;
			}

			pw.write(sb.toString());
			pw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return;
		}
		}
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		StdDraw.setCanvasSize(width, height);
		StdDraw.setPenRadius(0.005);
		StdDraw.setFont();
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		int size= this.list.size();
		double step = (rx.get_max()-rx.get_min())/resolution;
		
		for (int i=0; i <size ;i++) {
		
			StdDraw.setPenColor(Colors[i % Colors.length]);
			for(double x=rx.get_min(); x<rx.get_max(); x+=step) {
				double f0 = list.get(i).f(x);
				double f1 = list.get(i).f(x+step);
				//if(f0>ry.get_min() && f0<ry.get_max() && f0>ry.get_min() && f0<ry.get_max())
					StdDraw.line(x, f0, x+step, f1);
					
			}
		}
			
	}
	public class GUIparameters{
		int Width = 1000;
		int Height = 600;
		Range Range_X = new Range (-10, 10);
		Range Range_Y = new Range (-15, 5);
		int Resolution = 200;
		
	}
	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
		
		try 
		{
			FileReader reader = new FileReader(json_file);
			GUIparameters param = gson.fromJson(reader,GUIparameters.class);
			drawFunctions(param.Width, param.Height, param.Range_X, param.Range_Y, param.Resolution);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public function get(int i) {
		// TODO Auto-generated method stub
		return list.get(i);
	}

}
