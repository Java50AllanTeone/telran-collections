package telran.util;

import java.util.Iterator;

public class LinkedHashSet<T> implements Set<T> {
	HashMap<T, LinkedList.Node<T>> map = new HashMap<>();
	LinkedList<T> list = new LinkedList<>();

	@Override
	public boolean add(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object pattern) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public T get(Object pattern) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Object clone() {
		return null;
	}

}
