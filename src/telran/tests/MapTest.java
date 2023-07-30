package telran.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.Map;
import telran.util.Set;

abstract class MapTest {
	Integer [] arr = {1, 2, 3, 4, 5};
	Map<Integer, String> map;
	Set<Integer> expKeys;
	Collection<String> expValues;
	Set<Map.Entry<Integer, String>> expEntries;
	
//	protected abstract Collection<Integer> getMap(Integer[] ar);
	protected abstract void runCollectionTest(Collection<?> expected, Collection<?> actual);

	@BeforeEach
	void init() {
		for (var e : arr) {
			map.put(e, "" + e);
			expKeys.add(e);
			expValues.add("" + e);
			expEntries.add(new Map.Entry<Integer, String>(e, "" + e));
		}
	}

	
	@Test
	void getTest() {
		assertEquals("" + arr[0], map.get(arr[0]));
		assertNull(map.get(500));
	}
	

	@Test
	void getOrDefaultTest() {
		assertEquals("" + arr[0], map.getOrDefault(arr[0], "500"));
		assertEquals("500", map.getOrDefault(500, "500"));
	}
	
	
	@Test
	void removeTest() {
		assertEquals("" + arr[0], map.get(arr[0]));
		assertNull(map.get(500));
	}
	
	@Test
	void putTest() {
		int[] add = new int[]{6, 7};
		//adds
		var size = map.size();
		var res = map.put(add[0], "" + add[0]);
		
		assertNull(res);
		assertEquals(size + 1, map.size());
		assertEquals("" + add[0], map.get(add[0]));
		
		//not adds, exists
		size = map.size();
		res = map.put(add[0], "" + add[0]);
		assertEquals(size, map.size());
		assertEquals("" + add[0], res);
		
		//updates, exists
		size = map.size();
		res = map.put(add[0], "" + add[1]);
		assertEquals(size, map.size());
		assertEquals("" + add[0], res);
		assertEquals("" + add[1], map.get(add[0]));
		
		//exists but value is null
		map.put(add[0], null);
		res = map.put(add[0], "" + add[0]);
		assertNull(res);
	}
	
	@Test
	void containsKeyTest() {
		assertTrue(map.containsKey(1));
		assertFalse(map.containsKey(0));
	}
	
	@Test
	void putIfAbsentTest() {
		int[] add = new int[]{6, 7};
		//adds
		int size = map.size();
		var res = map.putIfAbsent(add[0], "" + add[0]);
		
		assertNull(res);
		assertEquals(size + 1, map.size());
		assertEquals("" + add[0], map.get(add[0]));
		
		//not adds, exists
		size = map.size();
		res = map.putIfAbsent(add[0], "" + add[0]);
		assertEquals(map.get(add[0]), res);
		assertEquals(size, map.size());
	}
	
	@Test
	void sizeTest() {
		assertEquals(5, map.size());
		map.remove(arr[4]);
		assertEquals(4, map.size());
		map.remove(arr[3]);
		assertEquals(3, map.size());
		map.remove(arr[2]);
		assertEquals(2, map.size());
		map.remove(arr[1]);
		assertEquals(1, map.size());
		map.remove(arr[0]);
		assertEquals(0, map.size());
		
		map.put(arr[0], "" + arr[0]);
		assertEquals(1, map.size());
	}
	
	@Test
	void isEmptyTest() {
		assertFalse(map.isEmpty());
		map.remove(arr[4]);
		map.remove(arr[3]);
		map.remove(arr[2]);
		map.remove(arr[1]);
		map.remove(arr[0]);

		assertTrue(map.isEmpty());
	}
	
	
	@Test
	void containsValueTest() {
		assertTrue(map.containsValue("" + arr[0]));		
		assertFalse(map.containsValue("500"));
	}
	
	@Test
	void keySetTest() {
		runCollectionTest(expKeys, map.keySet());
	}
	
	@Test
	void entrySetTest() {
		runCollectionTest(expEntries, map.entrySet());
	}
	
	@Test
	void valuesTest() {
		runCollectionTest(expValues, map.values());
	}
	
}
