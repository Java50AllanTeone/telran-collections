package telran.util;

public interface SortedSet<T> extends Set<T> {
	T first(); //least element
	T last(); //greatest element
	T ceiling(T key); //least among greater elements
	T floor(T key);
	

}
