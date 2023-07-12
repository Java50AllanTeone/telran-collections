package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class HashSet<T> implements Set<T> {
	private static final int DEFAULT_TABLE_LENGTH = 16;
	private LinkedList<T>[] hashTable;
	private float factor = 0.75f;
	private int size;
	
	@SuppressWarnings("unchecked")
	public HashSet(int tableLength) {
		hashTable = new LinkedList[tableLength];
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
		boolean res = false;
		int index = getIndex(pattern);
		LinkedList<T> list = hashTable[index];
		
		if (list != null) {
			res = list.remove(pattern);
			size--;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int counter = 0;
			int hashIndex = 0;
			Iterator<T> curIt;	
			boolean wasNext = false;

			public boolean hasNext() {
				return counter < size;
			}

			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				LinkedList<T> list = getList();
				
				if (curIt == null) {
					curIt = list.iterator();
				}
				
				if (!curIt.hasNext()) {
					hashIndex++;
					list = getList();
					curIt = list.iterator();
				}		
				counter++;
				wasNext = true;
				return curIt.next();
			}
			

			


			public void remove() {
				if (!wasNext) {
					throw new IllegalStateException();
				}
				
			}
			
			private LinkedList<T> getList() {
				var list = hashTable[hashIndex];

				while (hashTable[hashIndex] == null) {
					list = hashTable[++hashIndex];
				}
				return list;
			}
			
//			private LinkedList<T> nextList() {
//				LinkedList<T> list;
//				do {
//					list = hashTable[++curIndex];
//				} while (list == null);
//				return list;
//			}
			
			
			
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

}
