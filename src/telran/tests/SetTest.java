package telran.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import telran.util.Set;

public abstract class SetTest extends CollectionTest {

	@Override
	@Test
	void addTest() {
		assertFalse(collection.add(arr[0]));
		runArrayTest(arr, collection.toArray());
	}
	
	@Test
	void getPatternTest() {
		assertEquals(arr[1], ((Set<Integer>)collection).get(arr[1]));
		assertNull(((Set<Integer>)collection).get(1000));
	}

}
