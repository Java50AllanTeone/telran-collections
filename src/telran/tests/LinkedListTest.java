package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.LinkedList;

class LinkedListTest extends ListTest {
	LinkedList<Integer> ll;

	@Override
	@BeforeEach
	void init() {
		collection = new LinkedList<Integer>();
		super.init();
		ll = (LinkedList<Integer>) collection;
	}
	
	
	
	
	@Test
	void test() {
//		fail("Not yet implemented");
	}
	
	
	
	
	
	
	
	
	
	

}
