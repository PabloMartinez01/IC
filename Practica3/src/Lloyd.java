import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class Lloyd implements Algoritmo {
	
	private static final int MAX_ITERACIONES = 10;
	private static final double RAZON = 0.1;
	private static final double TOLERANCIA = 10e-10;
	
	
	
	private ArrayList<Matrix> centros;
	private ArrayList<Matrix> anteriores;
	private List<Clase> clases;
	private List<Matrix> ejemplos;
	
	private int times;
	
	public Lloyd(List<Clase> clases, List<Matrix> ejemplos) {
		
		this.clases = clases;
		this.ejemplos = ejemplos;
		
	}
	
	public void execute() {
		
		System.out.println("Ejecutando LLOYD");
		
		centros = new ArrayList<Matrix>();
		centros.add(new Matrix(m1));
		centros.add(new Matrix(m2));
		
		times = 0;
		
		ArrayList<Matrix> puntos = new ArrayList<Matrix>();
		
		clases.forEach((c) -> {c.getLista().forEach((e) -> {puntos.add(e);});});
		
		
		
		do {
			
			anteriores = (ArrayList<Matrix>) centros.clone();
			
			puntos.forEach((Matrix e) -> {actualizarCentro(getClase(e), e);});
			
			times++;
			
		}	
		while (!fin());
			
		
		System.out.println("Nuevos centros: ");
		
		new Matrix(new double[][] {centros.get(0).getColumnPackedCopy(), centros.get(1).getColumnPackedCopy()}).print(4, 4);;
		
		System.out.println("Clasificación\n--------------------");
	
			
		int n = 1;
		for (Matrix ejemplo : ejemplos) {
			
			System.out.println("Ejemplo " + n++);
			ejemplo.print(3, 3);
			
			
			for (int i = 0; i < clases.size();  i++) {
				System.out.println("Distancia a " + clases.get(i).getNombre() + " : " + Utils.distance(centros.get(i), ejemplo));
			}
			
					
			System.out.println("Pertenece a " + clases.get(getClase(ejemplo)).getNombre() + "\n");
				
			
		}
				
		
		
		
	}
	
	private boolean fin() {
		
		if (times >= MAX_ITERACIONES) return true;
		
		for (int i = 0; i < centros.size(); i++) {
			if (Utils.distance(centros.get(i), anteriores.get(i)) >= TOLERANCIA	)
				return false;
		}
		
		return true;
			
		
	}
	
	private int getClase(Matrix punto) {
		return Utils.distance(centros.get(0), punto) < Utils.distance(centros.get(1), punto) ? 0 : 1;
	}
	
	private Matrix actualizarCentro(int i, Matrix punto) {
		
		Matrix centro = centros.get(i);
		Matrix resta = punto.minus(centro);
		
		Matrix res = centro.plus(resta.times(RAZON));
		
		centros.set(i, res);
		return res;
	}
	

}
