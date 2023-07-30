package telran.util;

import java.util.Objects;

public interface Map<K, V> {
	public static class Entry<K, V> implements Comparable<Entry<K, V>>{
		private final K key;
		private V value;
		
		@Override
		public int hashCode() {
			return Objects.hash(key);
		}

		@SuppressWarnings("rawtypes")
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entry other = (Entry) obj;
			return Objects.equals(key, other.key);
		}

		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(Entry<K, V> o) {
			return ((Comparable<K>) key).compareTo(o.key);
		}
		
		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}	
	}



	V get(Object key);
	V remove(Object key);

	default V getOrDefault(Object key, V defaultValue) {
		V res = defaultValue;
		V value = get(key);
		
		if (value != null) {
			res = value;
		}
		return res;
	}
	
	
	V put(K key, V value);
	boolean containsKey(Object key);
	
	
	default V putIfAbsent(K key, V value) {
		V res = get(key);
		
		if (res == null) {
			put(key, value);
		}
		return res;
	}
	
	int size();
	
	default boolean isEmpty() {
		return size() == 0;
	}
	
	boolean containsValue(Object value);
	
	Set<K> keySet();
	Set <Entry<K, V>> entrySet();
	Collection<V> values();
	
	

}
