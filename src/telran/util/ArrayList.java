package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ArrayList<T> implements List<T>, Iterable<T> {
	private static final int DEFAULT_CAPACITY = 16;
	T[] array;
	private int size = 0;


	//constructors
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		this.array = (T[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayList(Collection<T> collection) {
		this();
		addAll(collection);
	}
	
	public ArrayList(T[] array) {
		this(array.length);
		addAll(array);
	}
	
	public ArrayList(T[] array, int capacity) {
		this(capacity);
		addAll(array);
	}


	//Collection methods
	@Override
	public boolean add(T obj) {
		if (size >= array.length) {
			reallocate((array.length * 3) / 2 + 1);
		}
		array[size++] = obj;
		return true;
	}

	@Override
	public void ensureCapacity(int capacity) {
		if (array.length < capacity)
			reallocate(capacity);
	}

	@Override
	public void trimToSize() {
		reallocate(size);
	}

	@Override
	public int size() {
		return size;
	}

	public int getCapacity() {
		return array.length;
	}
	
	@Override
	public Object[] toArray() {
		Object[] res = new Object[size];

		for (int i = 0; i < size; i++) {
			res[i] = array[i];
		}
		return res;
	}


	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(Object pattern) {
		for (int i = 0; i < size; i++) {
			if (Objects.equals((T)pattern, array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int lastLeft = -1;

		for (int i = 0; i < size / 2 + 1; i++) {
			if (Objects.equals(pattern, array[size - i - 1])) {
				return size - i - 1;
			}
			if (Objects.equals(pattern, array[i])) {
				lastLeft = i;
			}
		}
		return lastLeft;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		for (int i = 0; i < size; i++) {
			try {
				if (predicate.test(array[i])) {
					return i;
				}
			} catch (NullPointerException e) {}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int lastLeft = -1;

		for (int i = 0; i < size / 2 + 1; i++) {
			try {
				if (predicate.test(array[size - i - 1])) {
					return size - i - 1;
				}

				if (predicate.test(array[i])) {
					lastLeft = i;
				}
			} catch (NullPointerException e) {}
		}
		return lastLeft;
	}

	@Override
	public void add(int index, T object) {
		indexValidation(index, true);
		
		if (size >= array.length) {
			reallocate((array.length * 3) / 2 + 1);
		}
		
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = object;
		size++;
	}

	@Override
	public void set(int index, T object) {
		indexValidation(index, false);
		array[index] = object;
	}

	@Override
	public void sort(Comparator<T> c) {
		Arrays.sort(array, 0, size, c);
	}

	@Override
	public T get(int index) {
		indexValidation(index, false);

		return array[index];
	}
	
	@Override
	public T remove(int index) {
		indexValidation(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, 0, array, 0, index);
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}

	@Override
	public boolean addAll(int index, Collection<T> collection) {
		int oldSize = size();

		indexValidation(index, true);

		for (T e : collection) {
			add(index++, e);
		}
		return oldSize < size();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int current = 0;
			boolean flNext = false;

			public boolean hasNext() {
				return current < size();
			}

			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				flNext = true;
				return array[current++];
			}

			public void remove() {
				if (!flNext) {
					throw new IllegalStateException();
				}
				flNext = false;
				ArrayList.this.remove(--current);
			}
		};
	}

	public boolean addAll(T[] array) {
		int oldSize = size;
		
		for (T e : array) {
			this.add(e);
		}		
		return oldSize < size;
	}

	public void replaceAll(UnaryOperator<T> op) {
		for (int i = 0; i < size; i++) {
			array[i] = op.apply(array[i]);
		}
	}

	public Object clone() {
		return new ArrayList<>(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayList<T> other = (ArrayList<T>) obj;
		return size == other.size
				//copyOf for test
				&&  Arrays.deepEquals(Arrays.copyOf(array, size), Arrays.copyOf(other.array, size));
	}

	@Override
	public String toString() {
		return "[" + Arrays.toString(array) + ", size=" + size + "]";
	}


	

	//inner methods
	private void reallocate(int capacity) {
		array = Arrays.copyOf(array, capacity);
	}

	private void indexValidation(int index, boolean sizeInclusive) {
		int bounder = sizeInclusive ? size : size - 1;
		
		if (index < 0 || index > bounder) {
			throw new IndexOutOfBoundsException(index);
		}
	}
	
//	private void clearRange(int from, int to) {	
//		for (int i = from; i < to; i++) {
//			array[i] = null;
//		}
//	}
	
	
	//util test only methods

	
	public void setSize(int size) {
		this.size = size;
	}

}
