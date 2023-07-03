package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class CollectionTest {
	ArrayList<Integer> src;
	ArrayList<Integer> exp;
	
	@BeforeEach
	void init() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 6);
	}

	
	@Test
	void addTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6}, 6);
		assertTrue(src.add(6));
		assertEquals(exp, src);
		assertEquals(exp.size(), src.size());
		assertEquals(exp.getLength(), src.getLength());
		
		int size = src.size();
		src.add(7);
		assertEquals((size * 3) / 2 + 1, src.getLength());
	}
	

	@Test
	void removeTest() {
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4}, 6);
		assertTrue(src.remove(Integer.valueOf(5)));
		assertFalse(src.remove(Integer.valueOf(5)));
		
		assertEquals(exp, src);
		assertEquals(exp.size(), src.size());
		assertEquals(exp.getLength(), src.getLength());
	}
	
	
	@Test
	void toArrayWithTest() {
		var arrSrc = new Integer[3];
		var arrTrg = src.toArray(arrSrc);
		var exp = new Integer[]{1, 2, 3, 4, 5};
		assertNotEquals(Arrays.toString(arrTrg), Arrays.toString(arrSrc));
		assertArrayEquals(exp, arrTrg);
		
		arrSrc = new Integer[10];
		arrTrg = src.toArray(arrSrc);
		assertArrayEquals(arrTrg, arrSrc);
	}
	
	@Test
	void toArrayTest() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 5);
		var arr = src.toArray();
		var exp = new Object[]{1, 2, 3, 4, 5};
		assertArrayEquals(exp, arr);
		
		src.add(6);
		arr = src.toArray();
		exp = new Object[]{1, 2, 3, 4, 5, 6};
		assertArrayEquals(exp, arr);
	}
	
	
	@Test
	void removeIfTest() {
		assertFalse(src.removeIf(e -> false));
		assertTrue(src.removeIf(e -> e == 5));
		
		assertEquals(4, src.size());
		assertEquals(6, src.getLength());
		
		var exp = new Object[]{1, 2, 3, 4};
		assertArrayEquals(exp, src.toArray());
	}
	
	@Test
	void sizeTest() {
		assertEquals(5, src.size());
		src.add(6);
		assertEquals(6, src.size());
		src.add(7);
		assertEquals(7, src.size());
		src.remove(0);
		assertEquals(6, src.size());
	}
	
	@Test
	void addAllTest() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 10);
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10);
		var add = new ArrayList<>(new Integer[]{6, 7, 8, 9, 10}, 10);
		assertTrue(src.addAll(add));
		assertEquals(exp, src);
		
		add = new ArrayList<>(new Integer[0]);
		assertFalse(src.addAll(add));
		assertEquals(exp, src);
	}
	
	
	@Test
	void removeAllTest() {
		src = new ArrayList<>(new Integer[]{1, 2, 3, 4, 5}, 10);
		exp = new ArrayList<>(new Integer[]{1, 2, 3, 4}, 10);
		var remove = new ArrayList<>(new Integer[]{5}, 10);
		assertTrue(src.removeAll(remove));
		assertEquals(exp, src);
		
		remove = new ArrayList<>(new Integer[0]);
		assertFalse(src.addAll(remove));
		assertEquals(exp, src);
	}
	
	@Test
	void isEmptyTest() {
		src = new ArrayList<>(new Integer[]{}, 0);
		assertTrue(src.isEmpty());
		src = new ArrayList<>(new Integer[]{}, 10);
		assertTrue(src.isEmpty());
		
		src.add(1);
		assertFalse(src.isEmpty());
	}
	
	
	@Test
	void clearTest() {
		assertFalse(src.isEmpty());
		src.clear();
		assertTrue(src.isEmpty());
		assertEquals(0, src.size());
		assertArrayEquals(new Object[0], src.toArray());
	}
	

	@Test
	void containsTest() {
		assertTrue(src.contains(1));
		assertFalse(src.contains(6));
		
		src.clear();
		
		assertFalse(src.contains(1));
		assertFalse(src.contains(6));
	}

	
	@Test
	void ensureCapacityTest() {
		assertEquals(6, src.getLength());
		src.ensureCapacity(6);
		assertEquals(6, src.getLength());
		src.ensureCapacity(7);
		assertEquals(7, src.getLength());
		src.ensureCapacity(100);
		assertEquals(100, src.getLength());
	}
	
	@Test
	void retainAllTest() {
		var retain = new ArrayList<>(new Integer[]{1, 2, 3}, 6);
		
		assertTrue(src.retainAll(retain));
		assertFalse(src.retainAll(retain));
		assertEquals(retain, src);
		assertEquals(retain.size(), src.size());
		assertEquals(retain.getLength(), src.getLength());
		assertArrayEquals(retain.toArray(), src.toArray());
	}
	
	@Test
	void trimToSizeTest() {
		var arr = src.toArray();
		var size = src.size();
		
		assertNotEquals(size, src.getLength());
		src.trimToSize();
		assertEquals(src.size(), src.getLength());
		assertEquals(size, src.size());
		assertArrayEquals(arr, src.toArray());
	}
	
	@Test
	void iteratorTest() {
		var it = src.iterator();
		assertThrowsExactly(IllegalStateException.class, it::remove);
		it.next();
		it.remove();
		assertThrowsExactly(IllegalStateException.class, it::remove);
		
		while (it.hasNext())
			it.next();
		
		assertThrowsExactly(NoSuchElementException.class, it::remove);
	}
	
	
	
	
	
	
	
	

}
