import Jama.Matrix;

public class Utils {

	public static double distance(Matrix center, Matrix vector) {
		Matrix m = vector.minus(center);
		return m.times(m.transpose()).get(0, 0);
	}
	
	
	
	
}
