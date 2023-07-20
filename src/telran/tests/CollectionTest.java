package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.Collection;

abstract class CollectionTest {
	static final int N_BIG_NUMBERS = 10_000;
	static final int N_RUNS = 1000;
	Integer[] arr = {1, 2, 3, 4, 5};
	Collection<Integer> collection;
	Collection<Integer> exp;
	
	protected abstract void runArrayTest(Object[] expected, Object[] actual);
	protected abstract Collection<Integer> getCollection(Integer[] ar1);
	
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

		runArrayTest(exp.toArray(), collection.toArray());
		assertEquals(4, collection.size());
	}
	
	
	@Test
	void toArrayWithTest() {
		var arrSrc = new Integer[3];
		var arrTrg = collection.toArray(arrSrc);
		var exp = new Integer[]{1, 2, 3, 4, 5};
		assertNotEquals(Arrays.toString(arrTrg), Arrays.toString(arrSrc));
		runArrayTest(exp, arrTrg);
		
		arrSrc = new Integer[10];
		arrTrg = collection.toArray(arrSrc);
	}
	
	@Test
	void toArrayTest() {
		collection = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 5);
		var arr = collection.toArray();
		var exp = new Object[]{1, 2, 3, 4, 5};
		runArrayTest(exp, arr);
		
		collection.add(6);
		arr = collection.toArray();
		exp = new Object[]{1, 2, 3, 4, 5, 6};
		runArrayTest(exp, arr);
	}
	
	
	@Test
	void removeIfTest() {
		assertFalse(collection.removeIf(e -> false));
		assertTrue(collection.removeIf(e -> e == 5));
		
		assertEquals(4, collection.size());
		
		var exp = new Object[]{1, 2, 3, 4};
		runArrayTest(exp, collection.toArray());
	}
	
	@Test
	 void removeIfPerformanceTest() {
		 Integer[] bigArray = getBigArray();
		 Collection<Integer> bigCollection = null;
		 for(int i = 0; i < N_RUNS; i++) {
			 bigCollection = getCollection(bigArray);
			  bigCollection.clear();
			  assertEquals(0, bigCollection.size());
		 }
		 
		
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
		runArrayTest(new Object[0], collection.toArray());
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
		runArrayTest(arr, collection.toArray());
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
	
	@Test
	void cloneTest() throws Exception {
		@SuppressWarnings("unchecked")
		Collection<Integer>collection2 = (Collection<Integer>)collection.clone();
		assertEquals(collection, collection2);
		collection2.remove(arr[0]);
		assertFalse(collection2.contains(arr[0]));
		assertTrue(collection.contains(arr[0]));
		collection2.add(200);
		assertTrue(collection2.contains(200));
		assertFalse(collection.contains(200));
	}
	
	
	private Integer[] getBigArray() {
		Integer[] res = new Integer[N_BIG_NUMBERS];
		Random gen = new Random();
		for(int i = 0; i < N_BIG_NUMBERS; i++) {
			res[i] = gen.nextInt();
		}
		return res;
	}
	

	
	
	
	
	
	
	
	

}
