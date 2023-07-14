package telran.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
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
	
	@Override
	protected Collection<Integer> getCollection(Integer[] ar) {
		LinkedList<Integer> res = new LinkedList<>();
		for(Integer num: ar) {
			res.add(num);
		}
		return res;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
