package telran.util;

public interface Set<T> extends Collection<T> {
	T get(Object pattern);
	
	public boolean retainAll(Set<?> set);
	
	default public boolean setEquals(Object obj) {
		//TODO
		//Checks all elements are in same order using iteration
		return false;
	}
	

}
