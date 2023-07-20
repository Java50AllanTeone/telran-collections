package telran.util;

public interface SortedSet<T> extends Set<T> {
	T first(); //least element
	T last(); //greatest element
	T ceiling(T key); //least among greater than key or equals
	T floor(T key); //greatest among less than key or equal
	
	//Returns a shallow copy of the portion of this set whose elements are less than (or equal to, if inclusive is true) toElement.
	SortedSet<T> headSetCopy(T toElement, boolean inclusive);
	
	// Returns a shallow copy of the portion of this set whose elements are greater than (or equal to, if inclusive is true) fromElement.
	SortedSet<T> tailSetCopy(T fromElement, boolean inclusive);
	
	// Returns a shallow copy of the portion of this set whose elements range from fromElement to toElement.
	SortedSet<T> subSetCopy(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive);
	

}
