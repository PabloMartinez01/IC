

import java.util.ArrayList;
import java.util.List;

import Jama.*;

public class Main {

	public Main() {
		
	}

	public static void main(String[] args) {
	
		Loader l = new Loader();
		List<Clase> clases = l.loadFile();
		
		for (Clase c : clases) {
			
			System.out.println(c.getNombre());
			System.out.println("---------------");
			
			System.out.println("\n * Media");
			c.calculateMedia();
			
			System.out.println("\n * Covarianza");
			c.calculateCovar();
		}
		
		List<double[][]> ejs = new ArrayList<double[][]>();
		
		double[][] ej1 = {{5.1,3.5,1.4,0.2}};
		double[][] ej2 = {{6.9,3.1,4.9,1.5}};
		double[][] ej3 = {{5.0,3.4,1.5,0.2}};
		
		ejs.add(ej1);
		ejs.add(ej2);
		ejs.add(ej3);
		
		
		int i = 1;
		for (double[][] ej : ejs) {
			
			Matrix m = new Matrix(ej);
			System.out.println("\n\nEjemplo " + i++);
			m.print(4, 4);
			
			Double menor = 1000.0;
			Clase mejorClase = null;
			
			for (Clase c : clases) {
				
				Double dis = c.getDistancia(m);
				
				if (dis < menor) {
					menor = dis;
					mejorClase = c;
				}
				
				System.out.println("Distancia a " + c.getNombre() + " " + dis);
				
			}
			
			System.out.println("Pertenece a " + mejorClase.getNombre());
		}
		
	
		
	

	}

}
