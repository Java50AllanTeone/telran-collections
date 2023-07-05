package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

abstract class CWCollectionTest {
	Integer[] numbers = {10, -20, 8, 14, 30, 12, 100};
	Collection<Integer> collection;
	
	@BeforeEach
	void setUp() {
		for (Integer num : numbers) {
			collection.add(num);
		}
	}

	@Test
	abstract void addTest();
	
	@Test
	void removeTest() {
		Integer[] expected1 = {-20, 8, 14, 30, 12, 100};
		Integer[] expected2 = {-20, 8, 14, 30, 12};
		Integer[] expected3 = {-20, 8, 30, 12};
		
		assertTrue(collection.remove(10));
		assertArrayEquals(expected1, collection.toArray(new Integer[0]));
		assertTrue(collection.remove(100));
		assertArrayEquals(expected2, collection.toArray(new Integer[0]));
		assertTrue(collection.remove(14));
		assertArrayEquals(expected3, collection.toArray(new Integer[0]));
		
		assertFalse(collection.remove(1000000));
		assertArrayEquals(expected3, collection.toArray(new Integer[0]));
	}
	
	@Test
	void clearTest() {
		collection.clear();
		assertEquals(0, collection.size());
	}
	
	@Test
	void containsTest() {
		assertTrue(collection.contains(14));
		assertFalse(collection.contains(1000000));
	}
	
	@Test
	void containsAllTest() {
		Integer[] ar1 = {10, -20};
		Integer[] ar2 = {10, 100000};
		Collection<Integer> col1 = getCollection(ar1);
		Collection<Integer> col2 = getCollection(ar2);
		assertTrue(collection.containsAll(col1));
		assertFalse(collection.containsAll(col2));
	}
	
	@Test
	void toArrayTest

	protected abstract Collection<Integer> getCollection(Integer[] ar);

}
