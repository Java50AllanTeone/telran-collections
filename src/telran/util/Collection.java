package telran.util;

import java.util.Arrays;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);
	boolean addAll(Collection<T> collection);
	boolean remove(Object pattern);
	boolean removeAll(Collection<T> collection);
	boolean retainAll(Collection<T> c);
	boolean isEmpty();
	boolean contains(Object o);
	void clear();
	void ensureCapacity(int capacity);
	void trimToSize();
	int size();
	Object[] toArray();
	
	

	default public T[] toArray(T[] ar) {
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
	
	default public boolean removeIf(Predicate<T> predicate) {
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
}
