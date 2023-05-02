import java.util.List;

import Jama.Matrix;

public class Bayes implements Algoritmo {
	
	private List<Clase> clases;
	private List<Matrix> ejemplos;

	public Bayes(List<Clase> clases, List<Matrix> ejemplos) {
		this.clases = clases;
		this.ejemplos = ejemplos;
	}
	
	public void execute() {
	
		System.out.println("Ejecutando BAYES");
		
		for (Clase c : clases) {
			
			System.out.println(c.getNombre());
			System.out.println("---------------");
			
			System.out.println("\n * Media");
			c.calculateMedia();
			
			System.out.println("\n * Covarianza");
			c.calculateCovar();
		}
		
	
		
		
		int i = 1;
		for (Matrix m : ejemplos) {
			
		
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
