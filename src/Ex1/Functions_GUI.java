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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;



public class Functions_GUI implements functions   {
	
	
 //LinkedList<function> list= new LinkedList<function>();
	LinkedList<function> list;
	
 
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

	
	
	
	
	
	/**
	* This method gets a text file string, reads it, and puts all the functions in the file into the gui list
	@param file represents the file we received
	*/
	@Override
	public void initFromFile(String file) throws IOException {	
		// TODO Auto-generated method stub
		try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(file));     
        	String line = br.readLine();

            while (line != null ) {
    			int indexFx = line.indexOf("f(x)=");
    			if (indexFx != -1) { 
    				line = line.substring(indexFx+"f(x)=".length() ,line.length()); 
    			}
    			function f = new ComplexFunction().initFromString(line);
    			list.add(f); 
    			
    			line = br.readLine();
    		}
            br.close();
            }
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("could not read file");
        }
	}

	
	
	
	
	/**
	* This method obtains a text file string and saves all the functions in the file to the gui list
	@param file represents the file we received
	*/
	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub		
		try 
		{
			PrintWriter pw = new PrintWriter(new File(file));
			
			StringBuilder sb = new StringBuilder();
			int i=0;
			while (this.list.size()>i) {
				function f = list.get(i);
				pw.write(i + ") " + "f(x)=" + f.toString() + '\n');
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
	
	
	
	
	
	/**
	* This method recuves 4 variables representing height, width, resolution, X range and y range and draws the functions according to the variables we received.
	@param height represents the Canvas height 
	@param width represents the length range of the Canvas
	@param resolution The resolution we want you to draw
	@param rx, ry - The range of x and y in the drawing
	*/
	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		double  front;
		double Behind;
		StdDraw.setCanvasSize(width, height);
		StdDraw.setPenRadius(0.005);
		StdDraw.setFont();
		StdDraw.setXscale(rx.get_min(), rx.get_max());
		StdDraw.setYscale(ry.get_min(), ry.get_max());

		for (int i = (int)ry.get_min(); i < ry.get_max(); i++) {
			if(i != 0) {				
			StdDraw.line(-0.2, i, 0.2, i);
			StdDraw.text(0.4, i, i+"");
			}
		}
		for (int j = (int)rx.get_min(); j < rx.get_max(); j++) {
			if(j != 0) {
			StdDraw.text(j, -0.5, j+"");
			StdDraw.line(j, -0.2, j, 0.2);
			}
		}
		StdDraw.line(rx.get_min(), 0, rx.get_max(), 0);
		StdDraw.line(0, ry.get_min(), 0, ry.get_max());
		
		
		double step = (rx.get_max()-rx.get_min())/resolution;
		
		for (int i = 0; i < size(); i++) {
			StdDraw.setPenColor(Colors[i % Colors.length]);
			try {
				Behind =  get(i).f(rx.get_min());
				
			} catch (Exception e) {
				Behind=Integer.MIN_VALUE;
			}
			for (double x = rx.get_min(); x < rx.get_max(); x += step) {
				try {
					front =  get(i).f(x+step);
					if(Behind != Integer.MIN_VALUE && front != Integer.MIN_VALUE)
						StdDraw.line(x, Behind, x+step, front);
				}
				catch (Exception e)
				{
					front = Integer.MIN_VALUE;
				} 
				Behind = front;										
				
			}	
		}
	}
	
	private function get(int i) {
		// TODO Auto-generated method stub
		return list.get(i);
	}
	
	/**
	* This method obtains a JSON file string and calls the drawing function with the variables extracted from the JSON file
	@param json_file The string of the JSON file we receive and from which we take the variables to the drawing function
	*/
	@Override
	public void drawFunctions(String json_file) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
        try {

            BufferedReader br = new BufferedReader(new FileReader(json_file));
 
            JsonObject objectParams = gson.fromJson(br, JsonObject.class);
            
            int height = objectParams.get("Height").getAsInt();
            int width = objectParams.get("Width").getAsInt();
            Range rx, ry;
            JsonArray rangePara = objectParams.get("Range_X").getAsJsonArray();
            rx = new Range(rangePara.get(0).getAsInt(), rangePara.get(1).getAsInt());
            rangePara = objectParams.get("Range_Y").getAsJsonArray();
            ry = new Range(rangePara.get(0).getAsInt(), rangePara.get(1).getAsInt());
            int resolution = objectParams.get("Resolution").getAsInt();
            
            drawFunctions(width, height, rx, ry, resolution);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	
	}
	
}
