

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import Jama.Matrix;

public class Main {
	

	public Main() {
		
	}

	public static void main(String[] args) {
		
		
		
		Loader l = new Loader();
		
		List<Clase> clases = l.loadFile();
		List<Matrix> ejemplos = l.loadExamples();
		
		
		HashMap<String, Algoritmo> map = new HashMap<String, Algoritmo>();
		map.put("b", new Bayes(clases, ejemplos));
		map.put("k", new Kmedias(clases, ejemplos));
		map.put("l", new Lloyd(clases, ejemplos));
		
		Scanner scanner = new Scanner(System.in);
		
		String option;
		System.out.print("Introduce una opción [b/k/l] : ");
		
		while(!(option = scanner.nextLine()).equals("exit")) {
			
			if (map.containsKey(option))
				map.get(option).execute();	
			else 
				System.out.println("Error");
			
			System.out.print("\nIntroduce una opción [b/k/l] : ");
		}
		
		
		
						

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
