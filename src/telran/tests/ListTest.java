package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ListTest {
	ArrayList<Integer> src;
	ArrayList<Integer> exp;

	@BeforeEach
	void init() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
	}
	
	@Test
	void addTest() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 10);
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6}, 10);
		src.add(5, 6);
		assertEquals(exp, src);
		
		exp = new ArrayList<>(new Integer[]{0, 1, 2, 3, 4, 5, 6}, 10);
		src.add(0, 0);
		assertEquals(exp, src);
		
		exp = new ArrayList<>(new Integer[]{0, 1, 2, 3, 9, 4, 5, 6}, 10);
		src.add(4, 9);
		assertEquals(exp, src);
		
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 5);
		exp = new ArrayList<>(new Integer[]{0, 1, 2, 3, 4, 5}, 8);
		src.add(0, 0);
		assertEquals(exp, src);
	}
	
	
	@Test
	void addAllTest() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 10);
		var add = new ArrayList<>(new Integer[]{1, 2}, 10);
		exp = new ArrayList<>(new Integer[]{1, 2, 1, 2, 3, 4, 5}, 10);
		src.addAll(0, add);
		assertEquals(exp, src);
		
		exp = new ArrayList<>(new Integer[]{1, 2, 1, 2, 3, 4, 5, 1, 2}, 10);
		src.addAll(7, add);
		assertEquals(exp, src);
	}
	

	@Test
	void getTest() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> src.get(100));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> src.get(-1));
		assertEquals(1, src.get(0));
		assertEquals(5, src.get(4));
	}
	
	@Test
	void setTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		assertEquals(exp, src);
		
		src.set(0, 1);
		assertEquals(exp, src);
		
		src.set(0, 0);
		assertNotEquals(exp, src);
		
		exp = new ArrayList<>(new Integer[]{0, 2, 3, 4, 5}, 6);
		assertEquals(exp, src);
	}
	

	@Test
	void removeTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		assertEquals(exp, src);
		
		src.remove(0);
		exp = new ArrayList<>(new Integer[]{2, 3, 4, 5}, 6);
		assertEquals(exp, src);
		
		src.remove(3);
		exp = new ArrayList<>(new Integer[]{2, 3, 4}, 6);
		assertEquals(exp, src);
		
		src.remove(1);
		exp = new ArrayList<>(new Integer[]{2, 4}, 6);
		assertEquals(exp, src);
	}
	
	
	@Test
	void indexOfTest() {
		src = new ArrayList<>(new Integer[]{1, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(0, src.indexOf(1));
		
		src = new ArrayList<>(new Integer[]{0, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(1, src.indexOf(1));
		
		src = new ArrayList<>(new Integer[]{0, 2, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(4, src.indexOf(1));
		
		src = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, 1}, 10);
		assertEquals(9, src.indexOf(1));
		
		src = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 1}, 10);
		assertEquals(0, src.indexOf((Integer) null));
		
		src = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, null}, 10);
		assertEquals(9, src.indexOf((Integer) null));
		
		src = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, src.indexOf(1));
	}
	
	@Test
	void lastIndexOfTest() {
		src = new ArrayList<>(new Integer[]{1, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(9, src.lastIndexOf(1));
		
		src = new ArrayList<>(new Integer[]{0, 1, 2, 3, 1, 1, 4, 5, 0, 0}, 10);
		assertEquals(5, src.lastIndexOf(1));
		
		src = new ArrayList<>(new Integer[]{1, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, src.lastIndexOf(1));
		
		src = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, null}, 10);
		assertEquals(9, src.lastIndexOf((Integer) null));
		
		src = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, src.lastIndexOf((Integer) null));
		
		src = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, src.lastIndexOf(1));
	}
	
	@Test
	void indexOfPredicateTest() {
		src = new ArrayList<>(new Integer[]{1, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(0, src.indexOf(e -> e == 1));
		
		src = new ArrayList<>(new Integer[]{0, 0, 2, 3, 1, 1, 4, 5, 0, 0}, 10);
		assertEquals(4, src.indexOf(e -> e == 1));
		
		src = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, 1}, 10);
		assertEquals(9, src.indexOf(e -> e == 1));
		
		src = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, null}, 10);
		assertEquals(9, src.indexOf(e -> e == null));
		
		src = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, src.indexOf(e -> e == null));
		
		src = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, src.indexOf(e -> e == 1));
		
		src = new ArrayList<>(new Integer[]{null, 1, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(1, src.indexOf(e -> e.toString().equals("1")));
		
		src = new ArrayList<>(new Integer[]{null, 0, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, src.indexOf(e -> e.toString().equals("1")));
	}

	
	@Test
	void lastIndexOfPredicateTest() {
		src = new ArrayList<>(new Integer[]{1, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(9, src.lastIndexOf(e -> e == 1));
		
		src = new ArrayList<>(new Integer[]{0, 0, 2, 3, 1, 1, 4, 5, 0, 0}, 10);
		assertEquals(5, src.lastIndexOf(e -> e == 1));
		
		src = new ArrayList<>(new Integer[]{1, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, src.lastIndexOf(e -> e == 1));
		
		src = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, null}, 10);
		assertEquals(9, src.lastIndexOf(e -> e == null));
		
		src = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, src.lastIndexOf(e -> e == null));
		
		src = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, src.lastIndexOf(e -> e == 1));
		
		src = new ArrayList<>(new Integer[]{null, 1, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(1, src.lastIndexOf(e -> e.toString().equals("1")));
		
		src = new ArrayList<>(new Integer[]{null, 0, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, src.lastIndexOf(e -> e.toString().equals("1")));
	}
	
	@Test
	void sortTest() {
		src = new ArrayList<>(new Integer[]{1, 3, 2, 5, 4}, 5);
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 5);
		assertNotEquals(src, exp);
		
		src.sort((e1, e2) -> e1 - e2);
		assertEquals(exp, src);
		
		exp = new ArrayList<>(new Integer[]{5, 4, 3, 2, 1}, 5);
		src.sort((e1, e2) -> e2 - e1);
		assertEquals(exp, src);	
	}
	
	
	
	
	
	

}
