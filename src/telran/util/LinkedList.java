package telran.util;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;
		
		Node(T obj) {
			this.obj = obj;
		}

		@Override
		public String toString() {
			return "Node [obj=" + obj + "]";
		}
		
		
		
	}
	
	

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);

		return true;
	}

	@Override
	public int size() {
		return size;
	}


	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		var node = head;
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = node.obj;
			node = node.next;
		}
		return arr;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			Node<T> current = head;
			boolean wasNext = false;

			public boolean hasNext() {
				return current != null;
			}

			public T next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				wasNext = true;
				var res = current;
				current = current.next;
				return res.obj;
			}

			public void remove() {
				if (!wasNext) {
					throw new IllegalStateException();
				}
				wasNext = false;
				var cr = current == null ? tail : current.prev;
				size--;
				
				if (cr.prev == null) {
					removeHead(cr);
				} else if (cr.next == null) {
					removeTail(cr);
				} else {
					removeMiddle(cr);
				}
			}
		};
	}

	@Override
	public int indexOf(Object pattern) {
		var node = head;
		
		for (int i = 0; i < size; i++) {
			if (Objects.equals(node.obj, pattern)) {
				return i;
			}
			node = node.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		var node = tail;
		
		for (int i = size - 1; i >= 0; i--) {
			if (Objects.equals(node.obj, pattern)) {
				return i;
			}
			node = node.prev;
		}
		return -1;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		var node = head;
		
		for (int i = 0; i < size; i++) {
			if (predicate.test(node.obj)) {
				return i;
			}
			node = node.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		var node = tail;
		
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test(node.obj)) {
				return i;
			}
			node = node.prev;
		}
		return -1;
	}

	@Override
	public void add(int index, T object) {
		indexValidation(index, true);
		Node<T> node = new Node<>(object);
		addNode(index, node);
	}

	@Override
	public void set(int index, T object) {
		remove(index);
		add(index, object);
	}

	@Override
	public T get(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		return node.obj;
	}


	@Override
	public T remove(int index) {
		Node<T> node;
		
		try {
			indexValidation(index, false);
			node = getNode(index);

			if (index == 0) {
				removeHead(node);
			} else if (index == size - 1) {
				removeTail(node);
			} else {
				removeMiddle(node);
			}
			size--;
		} catch (IndexOutOfBoundsException e) {
			throw new NoSuchElementException(e);
		}
		
		return node.obj;
	}

	

	@Override
	public boolean addAll(int index, Collection<T> collection) {
		indexValidation(index, true);
		int prevSize = this.size;
		
		Node<T> nodeNext = getNode(index);
		Node<T> nodePrev = nodeNext.prev;
		
		for (T elem : collection) {
			nodePrev.next = new Node<T>(elem);
			nodePrev.next.prev = nodePrev;
			nodePrev = nodePrev.next;
			size++;
		}
		nodePrev.next = nodeNext;
		nodeNext.prev = nodePrev;
		return prevSize < size;
	}
	
	
	
	
	@Override
	public String toString() {
		String res = "";
		var temp = head;
		
		for (int i = 0; i < size(); i++) {
			res += temp + "->";
			temp = temp.next;
		}
		
		return res;
	}

	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromHead(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}
	
	private void addNode(int index, Node<T> node) {
		
		if (index == size) {
			addTail(node);
		} else if (index == 0) {
			addHead(node);
		} else {
			addMiddle(index, node);
		}
		size++;
	}


	private void addMiddle(int index, Node<T> node) {
		Node<T> nextNode = getNode(index);
		Node<T> prevNode = nextNode.prev;
		prevNode.next = node;
		node.prev = prevNode;
		nextNode.prev = node;
		node.next = nextNode;
	}


	private void addHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;	
	}


	private void addTail(Node<T> node) {
		if (tail == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
	}
	
	
	private void removeMiddle(Node<T> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = node.prev = null;
	}

	private void removeTail(Node<T> node) {
		tail = node.prev;
		node.prev.next = node.prev = null;
	}

	private void removeHead(Node<T> node) {		
		if (node.next == null ) {
			head = null;
			tail = null;
		} else {
			head = node.next;
			head.prev = null;
			node.next = null;
		}
		
	}


}
