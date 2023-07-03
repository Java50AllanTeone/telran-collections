package telran.util;

import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);
	boolean addAll(Collection<T> collection);
	boolean remove(Object pattern);
	boolean removeIf(Predicate<T> predicate);
	boolean removeAll(Collection<T> collection);
	boolean retainAll(Collection<T> c);
	boolean isEmpty();
	boolean contains(Object o);
	void clear();
	void ensureCapacity(int capacity);
	void trimToSize();
	int size();
	T[] toArray(T[] array);
	Object[] toArray();
}
