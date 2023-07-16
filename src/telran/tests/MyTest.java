package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.TreeSet;

class MyTest {

	@Test
	void test() {
		TreeSet<Integer> ts = new TreeSet();
		ts.add(10);
		ts.add(-20);
		ts.add(-10);
		ts.add(-5);
		ts.add(14);
		ts.add(12);
		ts.add(30);
		ts.add(100);
	}

}
