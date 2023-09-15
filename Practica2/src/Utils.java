import java.lang.Math;

public class Utils {
	
	private static final double LOG2 = 0.301029996;

	
	public Utils() {
		
	}
	
	private static Double log2(Double n) {
		return Math.log10(n) / LOG2;
	}
	
	public static Double information(Double p, Double n) {
		
		return -p * ((p == 0) ? 0 : log2(p)) - n * ((n == 0) ? 0 :log2(n));	
	
	}

}
