package telran.tests;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;

import telran.util.Collection;
import telran.util.LinkedHashSet;

public class LinkedHashSetTest extends SetTest {
	
	@BeforeEach
	@Override
	void init() {
		collection = new LinkedHashSet<Integer>();
		super.init();
	}

	@Override
	protected void runArrayTest(Object[] expected, Object[] actual) {
		assertArrayEquals(expected, actual);

	}

	@Override
	protected Collection<Integer> getCollection(Integer[] ar1) {
		Collection<Integer> res = new LinkedHashSet<Integer>();
		
		for (Integer num : ar1) {
			res.add(num);
		}
		return res;
	}

}
