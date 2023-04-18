import java.util.ArrayList;
import java.util.List;

import Jama.Matrix;

public class Clase {
	
	private String nombre;
	private List<Matrix> lista;
	private Matrix media;
	private Matrix cov;

	public Clase(String nombre) {
		this.nombre = nombre;
		this.lista = new ArrayList<Matrix>();
		this.media = null;
	}

	
	public void add(double[] m) {
		double[][] m2 = {m};
		lista.add(new Matrix(m2));
	}
	
	public void calculateMedia() {
		double[][] cero = {{0, 0, 0, 0}};
		Matrix res = new Matrix(cero);
		for (Matrix m : lista)
			res = res.plus(m);
		res = res.times((double)1/ (double)lista.size());
		this.media = res;
		media.print(4, 4);
	}
	
	public void calculateCovar() {
		
		List<Matrix> diff =  new ArrayList<Matrix>();
		
		for (Matrix m : lista) {
			diff.add(m.minus(this.media));
		}
		
		Matrix res = new Matrix(4, 4);
		
		for (Matrix m : diff) {
			res = res.plus(m.transpose().times(m));
		}
		
		res = res.times(1.0/(double) lista.size());
		
		cov = res;
		
		cov.print(4, 4);
			
	}
	
	public Double getDistancia(Matrix m) {
		
		
		Matrix dif = m.minus(media);
		Matrix inv = cov.inverse();
		
	
		Matrix res = dif.times(cov);
		res = res.times(dif.transpose());
		
		return res.get(0, 0);
	}
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Matrix getMedia() {
		return media;
	}


	public void setMedia(Matrix media) {
		this.media = media;
	}

	
	
	
	public void print() {
		for (Matrix m : lista) {
			m.print(1, 1);
		}
	}
	
	
}
