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
//import telran.util.TreeSet.Node;

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
		assertTrue(collection.contains(100));
		assertFalse(collection.contains(500));
		collection.clear();
		assertFalse(collection.contains(1));
	}

	@Test
	void firstTest() {
		assertEquals(Integer.valueOf(-20), treeSet.first());
		collection.clear();
		assertNull(treeSet.first());
	}

	@Test
	void lastTest() {
		assertEquals(Integer.valueOf(100), treeSet.last());
		collection.clear();
		assertNull(treeSet.last());
	}

	@Test
	void ceilingTest() {
		assertEquals(Integer.valueOf(-20), treeSet.ceiling(-30));
		assertEquals(Integer.valueOf(8), treeSet.ceiling(8));
		assertNull(treeSet.ceiling(200));
		collection.clear();
		assertNull(treeSet.ceiling(1));
	}
	
	@Test
	void floorTest() {
		assertEquals(Integer.valueOf(100), treeSet.floor(200));
		assertEquals(Integer.valueOf(100), treeSet.floor(100));
		assertNull(treeSet.floor(-30));
		collection.clear();
		assertNull(treeSet.ceiling(1));
	}
	
	
	@Test
	void displayRotatedTest() {
		treeSet.setSpacesPerLevel(4);
//		treeSet.displayRotated();
	}
	
	@Test
	void widthTest() {
		assertEquals(3, treeSet.width());
	}
	
	@Test
	void heightTest() {
		assertEquals(4, treeSet.height());
	}
	
	
	@Test
	void balanceTest() {
		treeSet.balance();
		assertEquals(4, treeSet.width());
		assertEquals(3, treeSet.height());
		
//		treeSet.displayRotated();
	}
	
	
	@Test
	void balanceTestArray() {
		Integer[] array = new Integer[1023];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		
//		array = reorderArray(array);
//		reorderArray(array);
		
		
		TreeSet<Integer> tree = new TreeSet<>();
		
		for (Integer num: array) {
			tree.add(num);
		}
		assertEquals(512, tree.width());
		
		
		tree.displayRotated();

	}



	private Integer[] reorderArray(Integer[] array) {
		Integer[] res = new Integer[array.length];
		balanceArray(array, 0, array.length - 1, res, 0);
		return res;
	}
	
	private int balanceArray(Integer[] array, int left, int right, Integer[] newArr, int index) {
		
		if (left <= right)  {
			int rootIndex = (left + right) / 2;
			newArr[index++] = array[rootIndex];
			index = balanceArray(array, left, rootIndex - 1, newArr, index);
			index = balanceArray(array, rootIndex + 1, right, newArr, index);	
		}	
		return index;
	}
	
	
	//v2
//	private void reorderArray(Integer[] array) {
//	balanceArray(array, 0, array.length - 1);
//}
//
//
//private void balanceArray(Integer[] array, int left, int right) {
//	int rootIndex = (left + right) / 2;
//	
//	if (left <= right)  {
//		
//		int temp = array[rootIndex];
//		array[rootIndex] = array[left];
//		array[left] = temp;
//	
//		balanceArray(array, left + 1, rootIndex);
//		balanceArray(array, rootIndex + 1, right);
//	} else if (right != rootIndex) {
//		right = rootIndex;
//	}
//}
	
	




	@Test
	void inverseTest() {
		Integer[] expected = {100, 30, 14, 12, 10, 8, -20};
		treeSet.inverse();
		assertArrayEquals(expected, treeSet.toArray(new Integer[0]));
		
		assertTrue(treeSet.contains(100));
	}
	
	
	

}
