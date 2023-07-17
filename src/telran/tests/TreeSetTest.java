package telran.tests;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import telran.util.Collection;
import telran.util.TreeSet;

class TreeSetTest extends SetTest {
	TreeSet<Integer> ts;
	
	@Override
	@BeforeEach
	void init() {
		collection = new TreeSet<Integer>();
		super.init();
		ts = (TreeSet<Integer>) collection;
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

}
