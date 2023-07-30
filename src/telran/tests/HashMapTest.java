package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import telran.util.Collection;
import telran.util.ArrayList;
import telran.util.HashMap;
import telran.util.HashSet;
import telran.util.Map;

class HashMapTest extends MapTest {
	HashMap<Integer, String> hm;

	@Override
	@BeforeEach
	void init() {
		map = new HashMap<Integer, String>();
		expKeys = new HashSet<Integer>();
		expEntries = new HashSet<Map.Entry<Integer, String>>();
		expValues = new ArrayList<String>();
		
		super.init();
		hm = (HashMap<Integer, String>) map;
	}

	@Override
	protected void runCollectionTest(Collection<?> expected, Collection<?> actual) {
		assertTrue(expected.size() == actual.size());
		
		var arr1 = expected.toArray();
		var arr2 = actual.toArray();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		assertArrayEquals(arr1, arr2);
	}

}
