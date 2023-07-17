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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object pattern) {
		
		return getNode((T)pattern) != null;
	}

	@Override
	public int size() {
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
		return new TreeIterator();
	}

	@Override
	public T first() {
		T res = null;
		
		if (root != null) {
			res = getLeastFrom(root).obj;
		}
		return null;
	}




	@Override
	public T last() {
		T res = null;
		
		if (root != null) {
			res = getGreatestFrom(root).obj;
		}
		return null;
	}




	@Override
	public T ceiling(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T floor(T key) {
		// TODO Auto-generated method stub
		return null;
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
		Node<T> next;
		Node<T> prev;
		boolean wasNext = false;
		
		@Override
		public boolean hasNext() {
			return root != null;
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
			return next.obj;
		}
		
		
		@Override
		public void remove() {
			if (!wasNext) {
			throw new IllegalStateException();
			}	
			next = getNext();
			
			if (prev.left == null && prev.right == null) {
				if (next == null) {
					TreeSet.this.root = null;
				} else {
					removeNode(prev, null);
				}
			} else if (prev.left == null || prev.right == null) {
				if (prev.parent == null) {
					removeRoot(prev);
				} else {
					removeNode(prev, prev.right == null ? prev.left : prev.right);
				}
			} else {
				removeNode(swapNode(prev), null);
			}
			size--;
		}
		
		private void removeRoot(Node<T> node) {		
			Node<T> next;
			if (node.right == null) {
				next = node.left;
				node.left = null;
			} else {
				next = node.right;
				node.right = null;
			}
			next.parent = null;
			TreeSet.this.root = next;
		}
		
		private void removeNode(Node<T> node, Node<T> target) {
			var prev = node.parent;
			
			if (prev.left == node) {
				prev.left = target;
			} else {
				prev.right = target;
			}
			target.parent = prev;
		}
		
		private Node<T> swapNode(Node<T> node) {
			var greatestFromLeast = getGreatestFrom(node.left);		
			node.obj = greatestFromLeast.obj;
			return greatestFromLeast;
		}
		
		
		
		
		
		
		private Node<T> getNext() {		
			var next = prev;
			
			while (comp.compare(next.obj, prev.obj) <= 0) {
				if (next.right == null || comp.compare(next.right.obj, prev.obj) <= 0) {
					next = next.parent;
					
					if (next == null) {
						return next;
					}
				} else {
					next = getLeastFrom(next.right);
				}
			}
			return next;
		}
	}
	
	 

}
