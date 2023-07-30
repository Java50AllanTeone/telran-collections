package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import telran.util.ArrayList;
import telran.util.Collection;
import telran.util.Map;
import telran.util.TreeMap;
import telran.util.TreeSet;

class TreeMapTest extends MapTest {
	TreeMap<Integer, String> tm;

	@Override
	@BeforeEach
	void init() {
		map = new TreeMap<Integer, String>();
		expKeys = new TreeSet<Integer>();
		expEntries = new TreeSet<Map.Entry<Integer, String>>();
		expValues = new ArrayList<String>();
		
		super.init();
		tm = (TreeMap<Integer, String>) map;
	}

	@Override
	protected void runCollectionTest(Collection<?> expected, Collection<?> actual) {
		assertTrue(expected.size() == actual.size());
		
		var arr1 = expected.toArray();
		var arr2 = actual.toArray();
		Arrays.sort(arr1);
		
		assertArrayEquals(arr1, arr2);
	}

}
