package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.List;

class ArrayListTest extends ListTest {
	ArrayList<Integer> al;

	@BeforeEach
	@Override
	void init() {
		collection = new ArrayList<>(6);
		super.init();
		al = (ArrayList<Integer>) collection;
	}

	@Test
	void addTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6}, 6);
		assertTrue(collection.add(6));
		assertEquals(exp, collection);
		assertEquals(exp.size(), collection.size());
		assertEquals(exp.getCapacity(), collection.getCapacity());
		
		int size = collection.size();
		collection.add(7);
		assertEquals((size * 3) / 2 + 1, collection.getCapacity());
	}
	
	@Test
	void constructorsTest() {
		collection = new ArrayList<>();
		exp = new ArrayList<>(16);
		assertEquals(exp, collection);
		
		collection = new ArrayList<>(exp);
		assertEquals(exp, collection);
		
		collection = new ArrayList<>(new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6));
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 16);
		assertEquals(exp, collection);
	}
	
	@Test
	void cloneTest() {
		var copy = al.clone();
		
		assertFalse(copy == al);
		assertEquals(copy, al);
	}
	
	
	@Test
	void replaceAllTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		var al = new ArrayList<>(collection);
		al.replaceAll(e -> e);
		assertEquals(exp, collection);
		
		exp = new ArrayList<>(new Integer[]{2, 3, 4, 5, 6}, 6);
		al.replaceAll(e -> e + 1);
		assertEquals(exp, al);
	}
	
	@Test
	void equalsTest() {
		exp = (ArrayList<Integer>) collection;
		assertTrue(collection.equals(exp));
		
		exp = null;
		assertFalse(collection.equals(exp));
		
		var exp1 = "str";
		assertFalse(collection.equals(exp1));
		
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		collection.add(6);
		assertFalse(collection.equals(exp));
	}
	
	
	@Test
	void toStringTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
		
		assertEquals(exp.toString(), collection.toString());
	}
	
	
	/*@Override
	protected Collection<Integer> getCollection(Integer[] ar1) {
		ArrayList<Integer> arrayList	 = new ArrayList<>();
		for (Integer num : ar1) {
			arrayList.add(num);
		}
		return arrayList;
	}*/

}
