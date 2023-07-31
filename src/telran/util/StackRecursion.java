package telran.util;



public class StackRecursion {
	
	
	public static void main(String[] args) {
		System.out.println(factorial(20) + " f!");
		fibonacci(20);
	}
	
	
	static long factorial(int num) {
		LinkedList<Integer> stack = new LinkedList<Integer>();
		long lastResult = 0;
		
		stack.add(num);
		
		do {
			int curParam = stack.get(stack.size() - 1);
			
			if (curParam == 1) {
				lastResult = curParam;
				stack.remove(stack.size() - 1);
			} else if (lastResult > 0) {
				lastResult *= curParam;
				stack.remove(stack.size() - 1);
			} else {
				stack.add(--curParam);
			}
			
		} while (!stack.isEmpty());
		return lastResult;
	}
	
	static long fibonacci(int num) {
		LinkedList<Frame> stack = new LinkedList<>();
		int lastRes = 0;
		
		do {
			if (num > 0) {
				stack.add(num == 1 ? new Frame(0, 1, -1) : new Frame());
				num--;
			} else {
				var curFrame = stack.get(stack.size() - 1);
				
				lastRes = curFrame.prev1 + curFrame.prev2;
				var prev2 = curFrame.prev1;
				stack.remove(stack.size() - 1);
				
				if (stack.size() > 0) {
					curFrame = stack.get(stack.size() - 1);
					curFrame.prev1 = lastRes;
					curFrame.prev2 = prev2;
				}
				System.out.print(lastRes + " ");
			}
		} while (stack.size() > 0);
		
		return lastRes;
	}
	
	
	
	
	public static class Frame {
		int value;
		int prev1;
		int prev2;
		public Frame(int value, int prev1, int prev2) {
			this.value = value;
			this.prev1 = prev1;
			this.prev2 = prev2;
		}
		
		public Frame() {
			this(0, 0, 0);
		}	
	}
	

}
