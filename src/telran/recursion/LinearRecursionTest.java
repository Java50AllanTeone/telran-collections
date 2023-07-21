package telran.recursion;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.LinearRecursion.*;
import org.junit.jupiter.api.Test;

class LinearRecursionTest {

	
	
	
	@Test
	void functionTest() {
		function(3);
		function(30);
	}
	
	@Test
	void factorialTest() {
		assertThrowsExactly(IllegalArgumentException.class, () -> factorial(-5));
		assertEquals(6, factorial(3));
		assertEquals(24, factorial(4));
	}

}
