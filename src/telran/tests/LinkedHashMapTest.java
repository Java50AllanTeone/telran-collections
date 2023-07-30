package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import telran.util.ArrayList;
import telran.util.Collection;
import telran.util.LinkedHashSet;
import telran.util.LinkedHashMap;
import telran.util.Map;

class LinkedHashMapTest extends MapTest {
	LinkedHashMap<Integer, String> hm;

	@Override
	@BeforeEach
	void init() {
		map = new LinkedHashMap<Integer, String>();
		expKeys = new LinkedHashSet<Integer>();
		expEntries = new LinkedHashSet<Map.Entry<Integer, String>>();
		expValues = new ArrayList<String>();
		
		super.init();
		hm = (LinkedHashMap<Integer, String>) map;
	}

	@Override
	protected void runCollectionTest(Collection<?> expected, Collection<?> actual) {
		assertTrue(expected.size() == actual.size());
		
		var arr1 = expected.toArray();
		var arr2 = actual.toArray();

		assertArrayEquals(arr1, arr2);
	}

}
