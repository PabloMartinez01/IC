import java.util.ArrayList;

public class Main {

	public Main() {
	
	}

	public static void main(String[] args) {	
		
		ArrayList<ArrayList<String>> data = Loader.loadData();
		ArrayList<String> header = Loader.loadHeader();
		
		TreeNode solution = new TreeNode(data, header, null);
		solution.id3();
		
		System.out.println("--------------------");
		System.out.println(" Arbol de decision ");
		System.out.println("--------------------\n");
		
		solution.print();
		
		System.out.println("\n--------------------");
		System.out.println("  Salida esperada ");
		System.out.println("--------------------\n");
		
		for (ArrayList<String> list : data) {
			solution.calculate(list, header);
		}
			
		
	}
	
	

}
