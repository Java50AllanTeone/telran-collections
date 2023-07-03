package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

public interface List<T> extends Collection<T> {
	int indexOf(T pattern);
	int lastIndexOf(T pattern);
	int indexOf(Predicate<T> predicate);
	int lastIndexOf(Predicate<T> predicate);
	void add(int index, T object);
	void set(int index, T object);
	void sort(Comparator<T> c);
	T get(int index);
	T remove(int index);
	boolean addAll(int index, Collection<T> collection);
}
