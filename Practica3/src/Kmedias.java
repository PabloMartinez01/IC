import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class Kmedias implements Algoritmo {
	
	private List<Clase> clases;
	private ArrayList<Matrix> v;
	
	private Matrix c1;
	private Matrix c2;
	
	private Matrix nc1;
	private Matrix nc2;
	
	private List<Matrix> centros;
	private ArrayList<ArrayList<Double>> U;
	private List<Matrix> ejemplos;
	
	
	public Kmedias(List<Clase> clases, List<Matrix> ejemplos) {
		this.clases = clases;
		this.v = new ArrayList<Matrix>();
		this.centros = new ArrayList<Matrix>();
		this.U = new ArrayList<ArrayList<Double>>();
		this.ejemplos = ejemplos;
	}
	
	public void execute() {
		
		System.out.println("Ejecutando KMEDIAS");
		
		getVectors();
		
		nc1 = new Matrix(m1);
		nc2 = new Matrix(m2);
		
		
		do {
			iteration();
		}
		while(!end());
		

		
		System.out.println("Nuevos centros: ");
		new Matrix(new double[][] {nc1.getColumnPackedCopy(), nc2.getColumnPackedCopy()}).print(4, 4);;
		
		
		ejecutarTests();
		
	}
	
	private void iteration() {
		c1 = (Matrix) nc1.clone();
		c2 = (Matrix) nc2.clone();
		
		centros.clear();
		centros.add(nc1);
		centros.add(nc2);
		
		calculateU();
		
		nc1 = calcularCentro(0);
		nc2 = calcularCentro(1);
	}
	
	private void calculateU() {
		
		U.clear();
		
		ArrayList<Double> u1 = new ArrayList<Double>();
		ArrayList<Double> u2 = new ArrayList<Double>();
		
		for (Matrix x : v) {
			
			u1.add(prob(0, x));
			u2.add(prob(1, x));
			
		}
		
		U.add(u1);
		U.add(u2);
		
	}
	
	private boolean end() {
		return Math.sqrt(Utils.distance(nc1, c1)) < 0.01 && Math.sqrt(Utils.distance(nc2, c2)) < 0.01;
	}
	
	
	
	private void getVectors() {
		v.clear();
		for (Matrix m : clases.get(0).getLista()) v.add(m);
		for (Matrix m : clases.get(1).getLista()) v.add(m);
	}
	
	private Double prob(int clase, Matrix vector) {
		
		ArrayList<Double> dist = new  ArrayList<Double>();
		double sumaInversas = 0;
		
		for (Matrix c : centros) {
			double r = Utils.distance(c, vector);
			dist.add(r);
			sumaInversas += 1.0 / r;
		}
		
		return  (1.0 / dist.get(clase)) / sumaInversas;
		
	}
	
	
	

	
	private Matrix calcularCentro(int clase) {
		
		double suma = 0.0;
		double[][] d = {{0.0, 0.0, 0.0, 0.0}};
		
		Matrix res = new Matrix(d);
		
		for (int i = 0; i < v.size(); i++) {
			double r = U.get(clase).get(i);
			r *= r;
			suma += r;
			Matrix m = v.get(i).times(r);
			
			res = res.plus(m);
		}
		
		return res.times(1.0 / suma);
		
	}
	
	private void ejecutarTests() {
		
		System.out.println("Clasificación\n-------------------");
		
		int n = 1;
		
		for (Matrix e : ejemplos) {
			
			System.out.println("Ejemplo " + n++);
			
			e.print(4, 4);
				
			double max = 0;
			Clase minClase = null;
			
			for (int i = 0; i < clases.size(); i++) {
				
				double g = prob(i, e);
				
				if (g > max) {
					max = g;
					minClase = clases.get(i);
				}
				
				
				System.out.println("G.Pertenencia a " + clases.get(i).getNombre() + " : " + g );
			}
			
			System.out.println("Pertenece a la clase " +  minClase.getNombre() + "\n\n");
			
		}
		
	
		
	}
	
	

}
