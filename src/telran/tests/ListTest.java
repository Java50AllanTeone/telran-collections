package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.List;

abstract class ListTest extends CollectionTest {

	List<Integer> list;
	List<Integer> exp;

	@BeforeEach
	@Override
	void init() {
		super.init();
		list = (List<Integer>) collection;
	}
	
	@Test
	void addTest() {
		list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 10);
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6}, 10);
		list.add(5, 6);
		assertEquals(exp, list);
		
		exp = new ArrayList<>(new Integer[]{0, 1, 2, 3, 4, 5, 6}, 10);
		list.add(0, 0);
		assertEquals(exp, list);
		
		exp = new ArrayList<>(new Integer[]{0, 1, 2, 3, 9, 4, 5, 6}, 10);
		list.add(4, 9);
		assertEquals(exp, list);
		
		list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 5);
		exp = new ArrayList<>(new Integer[]{0, 1, 2, 3, 4, 5}, 8);
		list.add(0, 0);
		assertEquals(exp, list);
	}
	
	
	@Test
	void addAllTest() {
		list = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 10);
		var add = new ArrayList<>(new Integer[]{1, 2}, 10);
		exp = new ArrayList<>(new Integer[]{1, 2, 1, 2, 3, 4, 5}, 10);
		list.addAll(0, add);
		assertEquals(exp, list);
		
		exp = new ArrayList<>(new Integer[]{1, 2, 1, 2, 3, 4, 5, 1, 2}, 10);
		list.addAll(7, add);
		assertEquals(exp, list);
	}
	

	@Test
	void getTest() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(100));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(-1));
		assertEquals(1, list.get(0));
		assertEquals(5, list.get(4));
	}
	
	@Test
	void setTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		assertEquals(exp, list);
		
		list.set(0, 1);
		assertEquals(exp, list);
		
		list.set(0, 0);
		assertNotEquals(exp, list);
		
		exp = new ArrayList<>(new Integer[]{0, 2, 3, 4, 5}, 6);
		assertEquals(exp, list);
	}
	

	@Test
	void removeTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		assertEquals(exp, list);
		
		list.remove(0);
		exp = new ArrayList<>(new Integer[]{2, 3, 4, 5}, 6);
		assertEquals(exp, list);
		
		list.remove(3);
		exp = new ArrayList<>(new Integer[]{2, 3, 4}, 6);
		assertEquals(exp, list);
		
		list.remove(1);
		exp = new ArrayList<>(new Integer[]{2, 4}, 6);
		assertEquals(exp, list);
	}
	
	
	@Test
	void indexOfTest() {
		list = new ArrayList<>(new Integer[]{1, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(0, list.indexOf(1));
		
		list = new ArrayList<>(new Integer[]{0, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(1, list.indexOf(1));
		
		list = new ArrayList<>(new Integer[]{0, 2, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(4, list.indexOf(1));
		
		list = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, 1}, 10);
		assertEquals(9, list.indexOf(1));
		
		list = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 1}, 10);
		assertEquals(0, list.indexOf((Integer) null));
		
		list = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, null}, 10);
		assertEquals(9, list.indexOf((Integer) null));
		
		list = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, list.indexOf(1));
	}
	
	@Test
	void lastIndexOfTest() {
		list = new ArrayList<>(new Integer[]{1, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(9, list.lastIndexOf(1));
		
		list = new ArrayList<>(new Integer[]{0, 1, 2, 3, 1, 1, 4, 5, 0, 0}, 10);
		assertEquals(5, list.lastIndexOf(1));
		
		list = new ArrayList<>(new Integer[]{1, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, list.lastIndexOf(1));
		
		list = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, null}, 10);
		assertEquals(9, list.lastIndexOf((Integer) null));
		
		list = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, list.lastIndexOf((Integer) null));
		
		list = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, list.lastIndexOf(1));
	}
	
	@Test
	void indexOfPredicateTest() {
		list = new ArrayList<>(new Integer[]{1, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(0, list.indexOf(e -> e == 1));
		
		list = new ArrayList<>(new Integer[]{0, 0, 2, 3, 1, 1, 4, 5, 0, 0}, 10);
		assertEquals(4, list.indexOf(e -> e == 1));
		
		list = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, 1}, 10);
		assertEquals(9, list.indexOf(e -> e == 1));
		
		list = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, null}, 10);
		assertEquals(9, list.indexOf(e -> e == null));
		
		list = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, list.indexOf(e -> e == null));
		
		list = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, list.indexOf(e -> e == 1));
		
		list = new ArrayList<>(new Integer[]{null, 1, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(1, list.indexOf(e -> e.toString().equals("1")));
		
		list = new ArrayList<>(new Integer[]{null, 0, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, list.indexOf(e -> e.toString().equals("1")));
	}

	
	@Test
	void lastIndexOfPredicateTest() {
		list = new ArrayList<>(new Integer[]{1, 1, 2, 3, 1, 1, 4, 5, 1, 1}, 10);
		assertEquals(9, list.lastIndexOf(e -> e == 1));
		
		list = new ArrayList<>(new Integer[]{0, 0, 2, 3, 1, 1, 4, 5, 0, 0}, 10);
		assertEquals(5, list.lastIndexOf(e -> e == 1));
		
		list = new ArrayList<>(new Integer[]{1, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, list.lastIndexOf(e -> e == 1));
		
		list = new ArrayList<>(new Integer[]{0, 2, 2, 3, 0, 0, 4, 5, 0, null}, 10);
		assertEquals(9, list.lastIndexOf(e -> e == null));
		
		list = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(0, list.lastIndexOf(e -> e == null));
		
		list = new ArrayList<>(new Integer[]{null, 2, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, list.lastIndexOf(e -> e == 1));
		
		list = new ArrayList<>(new Integer[]{null, 1, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(1, list.lastIndexOf(e -> e.toString().equals("1")));
		
		list = new ArrayList<>(new Integer[]{null, 0, 2, 3, 0, 0, 4, 5, 0, 0}, 10);
		assertEquals(-1, list.lastIndexOf(e -> e.toString().equals("1")));
	}
	
	@Test
	void sortTest() {
		list = new ArrayList<>(new Integer[]{1, 3, 2, 5, 4}, 5);
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 5);
		assertNotEquals(list, exp);
		
		list.sort((e1, e2) -> e1 - e2);
		assertEquals(exp, list);
		
		exp = new ArrayList<>(new Integer[]{5, 4, 3, 2, 1}, 5);
		list.sort((e1, e2) -> e2 - e1);
		assertEquals(exp, list);	
	}
	
	
	
	
	
	

}
