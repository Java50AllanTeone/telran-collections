package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.TreeSet;

class TreeAddTest {

	@Test
	void test() {
		TreeSet<Integer> ts = new TreeSet<>();
		ts.add(1);
		ts.add(0);
		ts.add(2);
		ts.add(5);
		ts.add(3);
		ts.add(-1);
		ts.add(0);
		ts.add(0);
		
		var it = ts.iterator();
	
		
		
		
		
		System.out.println(Arrays.toString(ts.toArray()));
	}

}
