package telran.util;

import java.util.Iterator;

public class LinkedHashSet<T> implements Set<T> {
	public HashMap<T, LinkedList.Node<T>> map = new HashMap<>();
	public LinkedList<T> list = new LinkedList<>();

	@Override
	public boolean add(T obj) {
		boolean res = false;
		
		if (!contains(obj)) {
			var node = new LinkedList.Node<T>(obj);
			list.addNode(list.size(), node);
			map.put(obj, node);
			res = true;
		}
		return res;
	}

	@Override
	public boolean remove(Object pattern) {
		boolean res = false;
		
		if (contains(pattern)) {
			map.remove(pattern);
			list.remove(pattern);
			res = true;
		}
		return res;
	}
	
	@Override
	public T get(Object pattern) {
		var res = map.get(pattern);
		return res == null ? null : res.obj;
	}

	@Override
	public boolean contains(Object o) {
		
		return map.containsKey(o);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Object[] toArray() {

		return list.toArray();
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}


	
	
	@Override
	public Object clone() {
		return null;
	}

}
