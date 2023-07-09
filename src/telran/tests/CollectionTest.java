package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.Collection;

abstract class CollectionTest {
	Integer[] arr = {1, 2, 3, 4, 5};
	Collection<Integer> collection;
	Collection<Integer> exp;
	
	@BeforeEach
	void init() {
		for (var e : arr) {
			collection.add(e);
		}
	}

	@Test
	abstract void addTest();
	
	
	@Test
	void removeTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4}, 6);
		assertTrue(collection.remove(Integer.valueOf(5)));
		assertFalse(collection.remove(Integer.valueOf(5)));
		
		assertEquals(exp, collection);
		assertEquals(4, collection.size());
	}
	
	
	@Test
	void toArrayWithTest() {
		var arrSrc = new Integer[3];
		var arrTrg = collection.toArray(arrSrc);
		var exp = new Integer[]{1, 2, 3, 4, 5};
		assertNotEquals(Arrays.toString(arrTrg), Arrays.toString(arrSrc));
		assertArrayEquals(exp, arrTrg);
		
		arrSrc = new Integer[10];
		arrTrg = collection.toArray(arrSrc);
		assertArrayEquals(arrTrg, arrSrc);
	}
	
	@Test
	void toArrayTest() {
		collection = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 5);
		var arr = collection.toArray();
		var exp = new Object[]{1, 2, 3, 4, 5};
		assertArrayEquals(exp, arr);
		
		collection.add(6);
		arr = collection.toArray();
		exp = new Object[]{1, 2, 3, 4, 5, 6};
		assertArrayEquals(exp, arr);
	}
	
	
	@Test
	void removeIfTest() {
		assertFalse(collection.removeIf(e -> false));
		assertTrue(collection.removeIf(e -> e == 5));
		
		assertEquals(4, collection.size());
		
		var exp = new Object[]{1, 2, 3, 4};
		assertArrayEquals(exp, collection.toArray());
	}
	
	@Test
	void sizeTest() {
		assertEquals(5, collection.size());
		collection.add(6);
		assertEquals(6, collection.size());
		collection.add(7);
		assertEquals(7, collection.size());

		collection.remove(1);
		assertEquals(6, collection.size());
	}
	
	@Test
	void addAllTest() {
		collection = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 10);
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10);
		var add = new ArrayList<>(new Integer[]{6, 7, 8, 9, 10}, 10);
		assertTrue(collection.addAll(add));
		assertEquals(exp, collection);
		
		add = new ArrayList<>(new Integer[0]);
		assertFalse(collection.addAll(add));
		assertEquals(exp, collection);
	}
	
	
	@Test
	void removeAllTest() {
		collection = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 10);
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4}, 10);
		var remove = new ArrayList<>(new Integer[]{5}, 10);
		assertTrue(collection.removeAll(remove));
		assertEquals(exp, collection);
		
		remove = new ArrayList<>(new Integer[0]);
		assertFalse(collection.addAll(remove));
		assertEquals(exp, collection);
	}
	
	@Test
	void isEmptyTest() {
		collection = new ArrayList<>(new Integer[]{}, 0);
		assertTrue(collection.isEmpty());
		collection = new ArrayList<>(new Integer[]{}, 10);
		assertTrue(collection.isEmpty());
		
		collection.add(1);
		assertFalse(collection.isEmpty());
	}
	
	
	@Test
	void clearTest() {
		assertFalse(collection.isEmpty());
		collection.clear();
		assertTrue(collection.isEmpty());
		assertEquals(0, collection.size());
		assertArrayEquals(new Object[0], collection.toArray());
	}
	

	@Test
	void containsTest() {
		assertTrue(collection.contains(1));
		assertFalse(collection.contains(6));
		
		collection.clear();

		assertFalse(collection.contains(1));
		assertFalse(collection.contains(6));
	}

	

	
	@Test
	void retainAllTest() {
		Integer[] arr = new Integer[] {1, 2, 3};
		var retain = new ArrayList<>(arr);
		assertTrue(collection.retainAll(retain));
		assertFalse(collection.retainAll(retain));
		assertArrayEquals(arr, collection.toArray());
	}
	

	
	@Test
	void iteratorTest() {
		var it = collection.iterator();
		assertThrowsExactly(IllegalStateException.class, it::remove);
		it.next();
		it.remove();
		assertThrowsExactly(IllegalStateException.class, it::remove);
		
		while (it.hasNext())
			it.next();
		
		assertThrowsExactly(NoSuchElementException.class, it::next);
	}
	
	
	
	
	
	
	
	

}
