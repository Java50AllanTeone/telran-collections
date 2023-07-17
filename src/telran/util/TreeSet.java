package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


@SuppressWarnings("unchecked")
public class TreeSet<T> implements SortedSet<T> {
	Node<T> root;
	int size;
	Comparator<T> comp;
	
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	

	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}
	
	
	
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;
		
		Node(T obj) {
			this.obj = obj;
		}
		
		private void swapNode(Node<T> node) {	
			var temp = this.obj;
			this.obj = node.obj;
			node.obj = temp;
	}
		
		private boolean isFullNode() {
			return this.left != null && this.right != null;
		}
		
		private boolean isMiddleNode() {
			return this.left == null | this.right == null;
		}
		
		private boolean isRoot() {
			return this.parent == null;
		}
			
	}

	@Override
	public T get(Object pattern) {
		Node<T> node = getNode((T)pattern);
		T res = null;
		
		if (node != null) {
			res = node.obj;
		}
		return res;
	}

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<T>(obj);
		boolean res = false;
		
		if (root == null) {
			res = true;
			root = node;
		} else {
			Node<T> parent = getParent(obj);
			
			if (parent != null) {
				res = true;
				node.parent = parent;
				int compRes = comp.compare(obj, parent.obj);
				
				if (compRes > 0) {
					parent.right = node;
				} else {
					parent.left = node;
				}
			}
		}
		if (res) {
			size++;
		}
		return res;
	}

	@Override
	public boolean remove(Object pattern) {
		var node = getNode((T)pattern);
		
		if (node == null)
			return false;
		
		remove(node);
		size--;	
		return true;
	}
	
	
	private void remove(Node<T> node) {
		if (node.isFullNode()) {
			var greatestFromLeast = getGreatestFrom(node.left);	
			node.swapNode(greatestFromLeast);
			node = greatestFromLeast;
		}
		
		if (node.isMiddleNode()) {
			removeNode(node, node.right == null ? node.left : node.right);
		} else {
			removeNode(node, null);
		}
	}
	
	
	private void removeNode(Node<T> node, Node<T> target) {
	if (node.isRoot()) {
		this.root = target;
	} else if (node.parent.left == node) {
		node.parent.left = target;
	} else {
		node.parent.right = target;
	}
	
	if (target != null) {
		target.parent = node.parent;
	}	
}

	@Override
	public boolean contains(Object pattern) {
		
		return getNode((T)pattern) != null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		var it = this.iterator();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = it.next();
		}
		return array;
	}

	@Override
	public Iterator<T> iterator() {
		return new TreeIterator();
	}

	@Override
	public T first() {
		T res = null;
		
		if (root != null) {
			res = getLeastFrom(root).obj;
		}
		return res;
	}




	@Override
	public T last() {
		T res = null;
		
		if (root != null) {
			res = getGreatestFrom(root).obj;
		}
		return res;
	}


	@Override
	public T ceiling(T key) {
		Node<T> node = getNode(key);
		
		if (node == null) {
			node = getParent(key);
		}
		while (comp.compare(node.obj, key) < 0 && node.parent != null) {
			node = node.parent;
		}	
		return comp.compare(node.obj, key) < 0 ? null : node.obj;
	}

	@Override
	public T floor(T key) {
		Node<T> node = getNode(key);
		
		if (node == null) {
			node = getParent(key);
		}
		while (comp.compare(node.obj, key) > 0 && node.parent != null) {
			node = node.parent;
		}	
		return comp.compare(node.obj, key) > 0 ? null : node.obj;
	}
	
	
	private Node<T> getParentOrNode(T key) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		
		while (current != null && (compRes = comp.compare(key, current.obj)) != 0) {
			parent = current;
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? parent : current;
	}
	
	
	private Node<T> getParent(T key) {
		Node<T> node = getParentOrNode(key);
		Node<T> parent = null;
		
		if (comp.compare(key, node.obj) != 0) {
			parent = node;
		}
		return parent;
	}
	
	private Node<T> getNode(T key) {
		Node<T> node = getParentOrNode(key);
		Node<T> res = null;
		
		if (node != null && comp.compare(key, node.obj) == 0) {
			res = node;
		}
		return res;
	}
	
	
	private Node<T> getLeastFrom(Node<T> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	private Node<T> getGreatestFrom(Node<T> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	
	class TreeIterator implements Iterator<T> {
		int index = 0 ;
		Node<T> next;
		Node<T> prev;
		boolean wasNext = false;
		
		@Override
		public boolean hasNext() {
			return index < size && root != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {	
				throw new NoSuchElementException();
			}	
			
			if (next == null) {
				next = getLeastFrom(root);
			} else if (next == prev) {
				next = getNext();
			}
			
			prev = next;
			wasNext = true;
			index++;
			return next.obj;
		}
		
		
		@Override
		public void remove() {
			if (!wasNext) {
			throw new IllegalStateException();
			}	
			next = getNext();
			
			TreeSet.this.remove(prev);
			size--;
			index--;
		}

		
		private Node<T> getNext() {		
			var next = prev;
			
			while (comp.compare(next.obj, prev.obj) <= 0) {
				if (next.right == null || comp.compare(next.right.obj, prev.obj) <= 0) {
					if (next.parent == null) {
						return next.parent;
					} else {
						next = next.parent;
					}
				} else {
					next = getLeastFrom(next.right);
				}
			}
			return next;
		}
	}
}
