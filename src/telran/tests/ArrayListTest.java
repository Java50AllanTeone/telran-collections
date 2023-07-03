package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ArrayListTest {
	ArrayList<Integer> src;
	ArrayList<Integer> exp;

	@BeforeEach
	void init() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
	}

	@Test
	void constructorsTest() {
		src = new ArrayList<>();
		exp = new ArrayList<>(16, 1);
		assertEquals(exp, src);
		
		src = new ArrayList<>(exp);
		assertEquals(exp, src);
		
		src = new ArrayList<>(new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6));
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 16);
		assertEquals(exp, src);
	}
	
	@Test
	void cloneTest() {
		var copy = src.clone();
		
		assertFalse(src == copy);
		assertEquals(src, copy);
	}
	
	
	@Test
	void replaceAllTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		src.replaceAll(e -> e);
		assertEquals(exp, src);
		
		exp = new ArrayList<>(new Integer[]{2, 3, 4, 5, 6}, 6);
		src.replaceAll(e -> e + 1);
		assertEquals(exp, src);
	}
	
	@Test
	void equalsTest() {
		exp = src;
		assertTrue(src.equals(exp));
		
		exp = null;
		assertFalse(src.equals(exp));
		
		var exp1 = "str";
		assertFalse(src.equals(exp1));
		
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		exp.setLoadFactor(0.5);
		assertFalse(src.equals(exp));
		
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		src.add(6);
		assertFalse(src.equals(exp));
	}
	
	
	@Test
	void toStringTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		
		assertEquals(exp.toString(), src.toString());
	}

}
