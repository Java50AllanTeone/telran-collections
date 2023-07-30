package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.Collection;

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
		exp = new ArrayList<>(new Integer[]{10, -20, 8, 14, 30, 12, 100, 6});
		assertTrue(collection.add(6));
		assertEquals(exp, collection);
		assertEquals(exp.size(), collection.size());
		var e = (ArrayList<Integer>) exp;
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
	void replaceAllTest() {
		exp = new ArrayList<>(new Integer[]{10, -20, 8, 14, 30, 12, 100}, 10);
		var al = new ArrayList<>(collection);
		al.replaceAll(e -> e);
		assertEquals(exp, collection);
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
		exp = new ArrayList<>(new Integer[]{10, -20, 8, 14, 30, 12, 100}, 10);
		
		assertEquals(exp.toString(), collection.toString());
	}
	
	@Test
	void trimToSizeTest() {
		var arr = collection.toArray();
		var size = collection.size();
		
		assertNotEquals(size, al.getCapacity());
		al.trimToSize();
		assertEquals(al.size(), al.getCapacity());
		assertEquals(size, al.size());
		assertArrayEquals(arr, al.toArray());
	}
	
	@Test
	void ensureCapacityTest() {
		assertEquals(10, al.getCapacity());
		al.ensureCapacity(6);
		assertEquals(10, al.getCapacity());
		al.ensureCapacity(7);
		assertEquals(10, al.getCapacity());
		al.ensureCapacity(100);
		assertEquals(100, al.getCapacity());
	}
	
	
	@Override
	protected Collection<Integer> getCollection(Integer[] ar1) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		for(Integer num: ar1) {
			arrayList.add(num);
		}
		return arrayList;
	}

}
