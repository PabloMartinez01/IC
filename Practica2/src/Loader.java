import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Loader {

	public Loader() {
		
	}
	
	public static ArrayList<String> loadHeader(){
		
		try (BufferedReader reader = new BufferedReader(new FileReader("./resources/AtributosJuego.txt"))){
			String line = reader.readLine();
			if (line != null)
				return new ArrayList<String> (Arrays.asList(line.split(",")));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static ArrayList<ArrayList<String>> loadData(){
		
		ArrayList<ArrayList<String>> matriz = new ArrayList<ArrayList<String>>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("./resources/Juego.txt"))){
			String line;
			while ((line = reader.readLine()) != null) {
				matriz.add(new ArrayList<String> (Arrays.asList(line.split(","))));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}


		return matriz;
	}

}
