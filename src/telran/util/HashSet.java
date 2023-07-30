package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class HashSet<T> implements Set<T>, Cloneable {
	private static final int DEFAULT_TABLE_LENGTH = 16;
	public LinkedList<T>[] hashTable;
	private float factor = 0.75f;
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashSet(int tableLength) {
		hashTable = new LinkedList[tableLength];
	}
	
	public HashSet(Collection<T> collection) {
		this();
		addAll(collection);
	}
	
	public HashSet() {
		this(DEFAULT_TABLE_LENGTH);
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		
		if ((float) size / hashTable.length >= factor) {
			hashTableRecreation();
		}
		int index = getIndex(obj);
		LinkedList<T> list = null;
		
		if (hashTable[index] == null) {
			hashTable[index] = new LinkedList<>();
		}
		list = hashTable[index];
		
		if (!list.contains(obj)) {
			res = true;
			list.add(obj);
			size++;
		}
		return res;
	}


	@Override
	public boolean remove(Object pattern) {
		T cur;
		boolean res = false;
		int index = getIndex(pattern);
		LinkedList<T> list = hashTable[index];
		
		if (list != null) {
			var it = list.iterator();
			
			while (it.hasNext()) {
				cur = it.next();
				
				if (Objects.equals(cur, pattern)) {
					it.remove();
					res = true;
					size--;
					break;
				}
			}
		}
		return res;
	}

	@Override
	public boolean contains(Object o) {
		boolean res = false;
		int index = getIndex(o);
		LinkedList<T> list = hashTable[index];
		
		if (list != null) {
			res = list.contains(o);
		}
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		var it = iterator();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = it.next();
		}
		return arr;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int counter = 0;
			int hashIndex = 0;
			int listIndex = 0;
//			Iterator<T> curIt;	
			boolean wasNext = false;

			public boolean hasNext() {
				return counter < size;
			}
			
			public T next() {
				if (!hasNext()) {			
					throw new NoSuchElementException();
				}
				
				if (listIndex == getList().size()) {
					hashIndex++;
					listIndex = 0;
				}
				counter++;
				wasNext = true;
				
				return getList().get(listIndex++);
			}

//			Iterator based
//			public T next() {
//				if (!hasNext()) {
//					throw new NoSuchElementException();
//				}
//				
//				if (curIt == null) {
//					curIt = getList().iterator();
//				}
//				
//				if (!curIt.hasNext()) {
//					hashIndex++;
//					curIt = getList().iterator();
//				}		
//				counter++;
//				wasNext = true;
//				return curIt.next();
//			}
			
			public void remove() {
				if (!wasNext) {
					throw new IllegalStateException();
				}			
				getList().remove(--listIndex);
//				curIt.remove();
				size--;
				counter--;
				wasNext = false;
			}
			
			private LinkedList<T> getList() {
				var list = hashTable[hashIndex];

				while (list == null || list.size() == 0) {
					list = hashTable[++hashIndex];
				}
				return list;
			}	
		};
	}

	@Override
	public T get(Object pattern) {
		T res = null;
		int index = getIndex(pattern);
		LinkedList<T> list = hashTable[index];
		
		if (list != null) {
			Iterator<T> it = list.iterator();
			
			while(it.hasNext() && res == null) {
				T obj = it.next();
				if (Objects.equals(pattern, obj)) {
					res = obj;
				}
			}
		}
		return res;
	}
	
	
	
	
	private int getIndex(Object obj) {
		int hashCode = obj.hashCode();
		
		return Math.abs(hashCode) % hashTable.length;
	}
	
	private void hashTableRecreation() {
		HashSet<T> tmp = new HashSet<>(hashTable.length * 2);
		for (LinkedList<T> list : hashTable) {
			if (list != null) {
				for (T obj : list) {
					tmp.add(obj);
				}
			}
		}
		hashTable = tmp.hashTable;
	}
	
	@Override
	public boolean equals(Object obj) {
		return setEqualsTo(obj);
	}

	@Override
	public Object clone() {
		return new HashSet<T>(this);
	}


}
