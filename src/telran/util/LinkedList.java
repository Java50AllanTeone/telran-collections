package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	Node<T> head;
	Node<T> tail;
	int size;
	
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;
		
		Node(T obj) {
			this.obj = obj;
		}
	}

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);

		return true;
	}

	
	@Override
	public void ensureCapacity(int capacity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trimToSize() {
		// TODO Auto-generated method stub

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(T pattern) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void add(int index, T object) {
		indexValidation(index, true);
		Node<T> node = new Node<>(object);
		addNode(index, node);
	}

	@Override
	public void set(int index, T object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sort(Comparator<T> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public T get(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		return node.obj;
	}


	@Override
	public T remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAll(int index, Collection<T> collection) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private Node<T> getNode(int index) {
		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i >= index; i--) {
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


}
