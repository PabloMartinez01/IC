import java.util.ArrayList;

public class Pair<T> {
	
	private T key;
	private ArrayList<String> value;

	public Pair(T key, ArrayList<String> value) {
		this.key = key;
		this.value = value;
	}
	
	public T getKey() {
		return key;
	}
	
	public ArrayList<String> getValue(){
		return value;
	}

}
