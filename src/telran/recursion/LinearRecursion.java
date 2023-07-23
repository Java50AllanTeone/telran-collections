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

//	public static long pow(int a, int b) {
//		long res = 1;
//		if (b < 0) {
//			throw new IllegalArgumentException("degree cannot be a negative");
//		}
//		if (b > 0) {
//			res = a * pow(a, b - 1);
//		}
//		return res;
//	}
	
	
	public static long pow(int a, int b) {
		long res = 1;
		
		if (b < 0)
			throw new IllegalArgumentException("degree cannot be a negative");
		if (b > 0)
			res = multAbs(a, pow(a, b - 1));
		return a < 0 && b % 2 != 0 ? -res : res;
	}
	
	
	public static long multAbs(long x, long y) {
		if (y < 0) 
			y = -y;
		if (x < 0) 
			x = -x;
		if (y > 1) 
			x += multAbs(x, y - 1);
		return x;
	}
	
	
	
	
	
	
	public static int square(int x) {
		if (x < 0) {
			x = -x;
		}
		return x > 0 ? x + square(x - 1) + x - 1 : 0;
	}
	
	

	public static void displayArray(int[] ar) {
		displayArray(0, ar, false);
	}

	private static void displayArray(int index, int[] ar, boolean isReverse) {
		if (index < ar.length) {
			if (isReverse) {
				displayArray(index + 1, ar, isReverse);
				System.out.print(ar[index] + " ");
			} else {
				System.out.print(ar[index] + " ");
				displayArray(index + 1, ar, isReverse);
			}
		}

	}

	public static void displayReversedArray(int[] ar) {
		displayArray(0, ar, true);
	}

	public static long sumArray(int[] array) {
		return sumArray(0, array);
	}

	private static long sumArray(int index, int[] array) {
		long res = 0;
		if (index < array.length) {
			res = array[index] + sumArray(index + 1, array);
		}
		return res;
	}

	public static void reverseArray(int[] array) {
		reverseArray(0, array, array.length - 1);
	}

	private static void reverseArray(int left, int[] array, int right) {
		if (left < right) {
			int tmp = array[left];
			array[left] = array[right];
			array[right] = tmp;
			reverseArray(left + 1, array, right - 1);
		}

	}




	public static boolean isSubstring(String string, String substr) {
		if (string.length() < substr.length()) {
			return false;
		}

		if (startsWith(string, substr)) {
			return true;
		}
		return isSubstring(string.substring(1), substr);
	}

	public static boolean startsWith(String string, String substr) {
		if (substr.length() == 0) {
			return true;
		}

		if (string.charAt(0) == substr.charAt(0)) {
			return startsWith(string.substring(1), substr.substring(1));
		}
		return false;
	}
}
