import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ID3 {
	

	public ID3() {
		
	}
	
	private static ArrayList<String> getColumn(ArrayList<ArrayList<String>> table, int n){
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i < table.size(); i++)
			res.add(table.get(i).get(n));
		return res;
	}
	
	public static ArrayList<ArrayList<String>> subMatriz(ArrayList<ArrayList<String>> table, int col, String campo){
		
		ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
		
		for (int i = 0; i < table.size(); i++) {
	
			ArrayList<String> row = new ArrayList<String>();
			
			if (table.get(i).get(col).equals(campo)) {
				
			
				for (int j = 0; j < table.get(i).size(); j++) {
					if (col != j)
						row.add(table.get(i).get(j));
					
				}
				
				boolean existe = false;
				for (ArrayList<String> row_res : res) {
					if (row_res.equals(row)) {
						existe = true;
						break;
					}
				}
				
				if (!existe) {
					res.add(row);
				}
			}
		}
		
		
		
		return res;
	}
	

	public static Pair<Integer> getBestColumn(ArrayList<ArrayList<String>> table) {
		
		ArrayList<String> jugar = getColumn(table, table.get(0).size() - 1);
		
		int mejorPos = 0;
		double mejorRes = Double.MAX_VALUE;
		
		ArrayList<String> opciones = null;
		
		for (int i = 0; i < table.get(0).size() - 1; i++) {
			
			ArrayList<String> col = getColumn(table, i);
			
			Pair<Double> res = id3Column(col, jugar);
			
			if (res.getKey() < mejorRes) {
				mejorRes = res.getKey();
				mejorPos = i;
				opciones = res.getValue();	
			}		
			
		}
		
		
		
		return new Pair<Integer> (mejorPos, opciones);
		
		
	}
	

	
	public static Pair<Double> id3Column(ArrayList<String> col, ArrayList<String> jugar) {
		
		HashMap<String, Integer> a = new HashMap<String, Integer>();
		HashMap<String, Integer> pn = new HashMap<String, Integer>();
		
		int i = 0;
		for (String valor : col) {
			
			if (a.containsKey(valor))
				a.put(valor, a.get(valor) + 1);
			else 
				a.put(valor, 1);
			
				
			
			if (pn.containsKey(valor)) {
				if (jugar.get(i).equals("si"))
					pn.put(valor, pn.get(valor) + 1);
			}
			else 
				pn.put(valor, (jugar.get(i).equals("si")) ? 1 : 0);
	
			
			i++;
		}
		
		Double resultado = 0.0;
		ArrayList<String> opciones = new ArrayList<String>();
		
		for (Map.Entry<String, Integer> set :  a.entrySet()) {
			
			String key = set.getKey();
			Double N = (double) col.size();
	
			Double p =  ((double) pn.get(key) / a.get(key));
			
			resultado += a.get(key)/N * Utils.information(p, 1 - p);
			opciones.add(key);
			
		}
		
		
		
		return new Pair<Double>(resultado, opciones);
		
	}

}
