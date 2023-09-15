import java.util.ArrayList;
import java.util.LinkedList;


public class TreeNode {
	
	private TreeNode parent;
	private LinkedList<TreeNode> children;
	
	private ArrayList<String> header;
	private ArrayList<ArrayList<String>> matriz;
	private String value;
	private String edge;

	public TreeNode(ArrayList<ArrayList<String>> matriz, ArrayList<String> header, String edge) {
		this.children = new LinkedList<TreeNode>();
		this.value = null;
		this.matriz = matriz;
		this.header = header;
		this.edge = edge;
	}
	
	public void appendChild(TreeNode child) {
		child.parent = this;
		this.children.add(child);
	}
	
	public void prependChild(TreeNode child) {
		child.parent = this;
		this.children.addFirst(child);
	}
	
	public String getValue() {
		return this.value;
	}
	
	public String getEdge() {
		return this.edge;
	}
	
	public  ArrayList<ArrayList<String>> getMatriz(){
		return this.matriz;
	}
	
	public void print() {
		dfs(this, 0);
	}
	
	private void dfs(TreeNode node, int level) {
			
		node.printNode(level);
		for (TreeNode t : node.children) 
			dfs(t, level + 1);
		
	}
	
	private void printNode(int level) {
		String space = new String(new char[15 * level]).replace('\0', ' ');
		
		if (edge != null)
			System.out.print(space + "· " +edge + " -> ");
		System.out.println(value);
	}
	
	public void id3() {
		
		if (this.matriz.size() == 0 || this.header.size() <= 1) {
			return;
		}
		
		
		int esSi = 0;	
		int size = matriz.get(0).size();
		
		for (ArrayList<String> v : matriz) {
			if (v.get(size - 1).equals("si"))
				esSi++;
		}
		
		if (esSi == 0) {
			this.value = "no"; return;
		}
		else if (esSi == matriz.size()) {
			this.value = "si"; return;
		}
		
		
		Pair<Integer> res = ID3.getBestColumn(matriz);
		
		Integer col = res.getKey();
		
		ArrayList<String> opciones = res.getValue();
		ArrayList<String> newHeader = new ArrayList<String>(this.header);
		
		newHeader.remove(col.intValue());
		
		
		this.value = this.header.get(col);
		
		
		for (String opcion : opciones) {
			ArrayList<ArrayList<String>> subMatriz = ID3.subMatriz(matriz, col, opcion);
			this.appendChild(new TreeNode(subMatriz, newHeader, opcion));
			
		}
		
		for (TreeNode child : this.children)
			child.id3();
		
		
		
		
	}
	
	public void calculate(ArrayList<String> data, ArrayList<String> header) {
		
		if (this.children.isEmpty()) 
			System.out.print(data + " : " + this.value + "\n");
			
		else {
			
			String opt = data.get(header.indexOf(value));		
			
			for (TreeNode child : this.children) {
				
				if (child.getEdge().equals(opt)) 
					child.calculate(data, header);
			
			}
			
		}
		
		
		
		
	}
	
	

}
