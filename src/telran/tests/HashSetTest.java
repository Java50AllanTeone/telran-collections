package telran.tests;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

import telran.util.HashSet;

public class HashSetTest extends SetTest {
	HashSet<Integer> hs;
	
	@Override
	@BeforeEach
	void init() {
		collection = new HashSet<Integer>(3);
		super.init();
		hs = (HashSet<Integer>) collection;
	}

	@Override
	protected void runArrayTest(Object[] expected, Object[] actual) {
		Object[] expectedSorted = Arrays.copyOf(expected, expected.length);
		Object[] actualSorted = Arrays.copyOf(actual, actual.length);
		Arrays.sort(expectedSorted);
		Arrays.sort(actualSorted);
		assertArrayEquals(expectedSorted, actualSorted);

	}

}
