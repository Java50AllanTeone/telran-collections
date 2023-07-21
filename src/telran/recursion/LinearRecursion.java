package telran.recursion;

public class LinearRecursion {
	
	public static void function(int a) {
		if (a > 3) {
			function(a - 1);
		}
		
	}
	
	
	public static long factorial(int n) {
		long res = 1;
		
		if (n < 0) {
			throw new IllegalArgumentException("factorial exists only for positive numbers and 0");
		}
		
		if (n > 0) {
			res = n * factorial(n - 1);
		}
		return res;
	}

}
