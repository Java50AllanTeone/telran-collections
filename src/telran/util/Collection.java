package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);
	boolean remove(Object pattern);
	boolean contains(Object o);
	int size();
	Object[] toArray();
	
	

	default T[] toArray(T[] ar) {
		int size = size();
		T[] res = ar.length < size ? Arrays.copyOf(ar, size) : ar;
		int index = 0;

		for (T obj : this) {
			res[index++] = obj;
		}
		if (res.length > size) {
			res[size] = null;
		}
		return res;
	}
	
	default boolean removeIf(Predicate<T> predicate) {
		int oldSize = size();
		var it = iterator();

		while (it.hasNext()) {
			T next = it.next();
			if (predicate.test(next)) {
				it.remove();
			}
		}
		return oldSize > size();
	}
	
	default boolean addAll(Collection<T> collection) {
		int oldSize = size();

		for (T e : collection) {
			this.add(e);
		}
		return oldSize < size();
	}
	
	default boolean removeAll(Collection<T> collection) {
		int oldSize = size();

		for (T e : collection) {
			this.remove(e);
		}
		return oldSize > size();
	}
	
	default void clear() {
		removeIf(n -> true);
	}
	
	default boolean retainAll(Collection<T> c) {
		int oldSize = size();

		removeIf(e -> !c.contains(e));
		return oldSize > size();
	}
	
	default boolean containsAll(Collection<T> collection) {
		boolean res = true;
		Iterator<T> it = collection.iterator();
		
		while (it.hasNext() && res) {
			T obj = it.next();
			res = contains(obj);
		}
		return res;
	}
	
	default boolean isEmpty() {
		return size() == 0;
	}
	
	
	

	
	
}
