package telran.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.TreeSet;

class TreeSetTest extends SetTest {
	TreeSet<Integer> treeSet;
	
	@Override
	@BeforeEach
	void init() {
		collection = new TreeSet<Integer>();
		super.init();
		treeSet = (TreeSet<Integer>) collection;
	}

	@Override
	protected void runArrayTest(Object[] expected, Object[] actual) {
		Object[] expectedSorted = Arrays.copyOf(expected, expected.length);
		Object[] actualSorted = Arrays.copyOf(actual, actual.length);
		Arrays.sort(expectedSorted);
		assertArrayEquals(expectedSorted, actualSorted);
		
	}

	@Override
	protected Collection<Integer> getCollection(Integer[] ar) {
		TreeSet<Integer> res = new TreeSet<>();
		for(Integer num: ar) {
			res.add(num);
		}
		return res;
	}

	@Test
	void getNodeTest() {
		assertTrue(collection.contains(arr[0]));
		assertTrue(collection.contains(1));
		assertFalse(collection.contains(500));
		collection.clear();
		assertFalse(collection.contains(1));
	}

	@Test
	void firstTest() {
		assertEquals(Integer.valueOf(1), treeSet.first());
		collection.clear();
		assertNull(treeSet.first());
	}

	@Test
	void lastTest() {
		assertEquals(Integer.valueOf(5), treeSet.last());
		collection.clear();
		assertNull(treeSet.last());
	}

	@Test
	void ceilingTest() {
		assertEquals(Integer.valueOf(1), treeSet.ceiling(-30));
		assertEquals(Integer.valueOf(2), treeSet.ceiling(2));
		assertNull(treeSet.ceiling(200));
		collection.clear();
		assertNull(treeSet.ceiling(1));
	}
	
	@Test
	void floorTest() {
		assertEquals(Integer.valueOf(5), treeSet.floor(200));
		assertEquals(Integer.valueOf(4), treeSet.floor(4));
		assertNull(treeSet.floor(-30));
		collection.clear();
		assertNull(treeSet.ceiling(1));
	}

	@Test
	void headSetCopyTest() {
		// TODO check method headSetCopy
//		fail();
	}
	
	@Test
	void tailSetCopyTest() {
		// TODO check method tailSetCopy
//		fail();
	}

	@Test
	void subSetCopyTest() {
		// TODO check method subSetCopy
//		fail();
	}

}
