package telran.util;


public interface Set<T> extends Collection<T> {
	T get(Object pattern);

	
	default boolean setEqualsTo(Object other) {
		try {
			return this.equalsTo((Set<?>) other);
		} catch (ClassCastException e) {
			return false;
		}
	}
	

}
