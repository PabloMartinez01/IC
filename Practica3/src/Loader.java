import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class Loader {
	
	private ArrayList<String> tests;

	public Loader() {
		tests = new ArrayList<String>();
		tests.add("./file/TestIris01.txt");
		tests.add("./file/TestIris02.txt");
		tests.add("./file/TestIris03.txt");
	}
	
	public List<Clase> loadFile() {
		
		List<Clase> res = new ArrayList<Clase>();
		
		Clase c1 = new Clase("Setosa");
		Clase c2 = new Clase("Versicolor");
	
		String file ="./file/Iris2Clases.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(file ))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        String[] splited = line.split(",");
		        
		        double[] valores = {
	        			Double.parseDouble(splited[0]), 
	        			Double.parseDouble(splited[1]), 
	        			Double.parseDouble(splited[2]),
	        			Double.parseDouble(splited[3])
	        			};
		        
		        if (splited[4].equals("Iris-setosa")) {
		        	c1.add(valores);
		        }
		        else {
		        	c2.add(valores);
		        }
		                
		    }
		   
		} catch (Exception e) {
			e.printStackTrace();
		} 
	

		res.add(c1);
		res.add(c2);
		
		return res;	
		
	}
	
	public List<Matrix> loadExamples(){
		
		List<Matrix> ejemplos = new ArrayList<Matrix>();
	
		tests.forEach((String file) -> {loadExampleFile(file, ejemplos);});
		
		return ejemplos;
		
	}
	
	private void loadExampleFile (String file, List<Matrix> examples) {
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        String[] splited = line.split(",");
		        
		        double[] valores = {
	        			Double.parseDouble(splited[0]), 
	        			Double.parseDouble(splited[1]), 
	        			Double.parseDouble(splited[2]),
	        			Double.parseDouble(splited[3])
	        			};
		        examples.add(new Matrix(new double[][]{valores}));
		    }
		   
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
