package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


@SuppressWarnings("unchecked")
public class TreeSet<T> implements SortedSet<T>, Cloneable {
	Node<T> root;
	int size;
	Comparator<T> comp;
	private int spacesPerLevel = 2;
	
	public int getSpacesPerLevel() {
		return spacesPerLevel;
	}

	public void setSpacesPerLevel(int spacesPerLevel) {
		this.spacesPerLevel = spacesPerLevel;
	}

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	
	public TreeSet(Collection<T> collection) {
		this();
		addAll(collection);
	}
	
	public TreeSet(Collection<T> collection, Comparator<T> comp) {
		this.comp = comp;
		addAll(collection);
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
		
		public void setNulls() {
			obj = null;
			parent = left = right = null;
			
		}
		
		private boolean isFullNode() {
			return this.left != null && this.right != null;
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

//	@Override
//	public boolean add(T obj) {
//		Node<T> node = new Node<T>(obj);
//		boolean res = false;
//		
//		if (root == null) {
//			res = true;
//			root = node;
//		} else {
//			Node<T> parent = getParent(obj);
//			
//			if (parent != null) {
//				res = true;
//				node.parent = parent;
//				int compRes = comp.compare(obj, parent.obj);
//				
//				if (compRes > 0) {
//					parent.right = node;
//				} else {
//					parent.left = node;
//				}
//			}
//		}
//		if (res) {
//			size++;
//		}
//		return res;
//	}
	
	@Override
	public boolean add(T obj) {
		int oldSize = size();
		root = addNode(root, obj);

		return size() > oldSize;
	}
	
	
	private Node<T> addNode(Node<T> root, T obj) {
		if (root != null) {
			int compRes = comp.compare(root.obj, obj);
			
			if (compRes > 0) {
				var left = addNode(root.left, obj);
				root.left = left;
				left.parent = root;
			}
			if (compRes < 0) {
				var right = addNode(root.right, obj);
				root.right = right;
				right.parent = root;
			} 
			return root;
		}
		size++;
		return new Node<>(obj);
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
			removeNode(node, node.right == null ? node.left : node.right);
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
		return checkNode(root, (T)pattern);
	}
	
	
	private boolean checkNode(Node<T> root, T obj) {
		boolean res = false;
		
		if (root != null) {
			res = comp.compare(root.obj, obj) == 0;
			
			if (!res) {
				res = checkNode(root.left, obj);
			}
			if (!res) {
				res = checkNode(root.right, obj);
			}	
		}	
		return res;
	}
	
	
//	private Node<T> checkNode(Node<T> root, T obj) {
//		Node<T> res = null;
//		
//		if (root != null) {
//			if (comp.compare(root.obj, obj) == 0) {
//				res = root;
//			}
//			
//			if (res == null) {
//				res = checkNode(root.left, obj);
//			}
//			
//			if (res == null) {
//				res = checkNode(root.right, obj);
//			}
//		}	
//		return res;
//	}
	


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
		var res = ceilingNode(key);
		return res == null ? null : res.obj;
	}
	

	private Node<T> ceilingNode(T key) {
		Node<T> node = null;
		if (root != null) {
			node = getParentOrNode(key); // never null
			int compRes = comp.compare(key, node.obj);
			if (compRes >  0) {
				node = getGreaterParent(node);
			}						
		}
		return node;
	}

	@Override
	public T floor(T key) {
		var res = floorNode(key);
		return res == null ? null : res.obj;
	}
	

	private Node<T> floorNode(T key) {
		Node<T> node = null;
		if (root != null) {
			node = getParentOrNode(key); // never null
			int compRes = comp.compare(key, node.obj);
			if (compRes <  0) {
				node = getLessParent(node);
			}						
		}
		return node;
	}
	
	
	
	
	private Node<T> getGreaterParent(Node<T> node) {
		while (node.parent != null && node.parent.left != node) {
			node = node.parent;
		}
		return node.parent;
	}

	private Node<T> getLessParent(Node<T> node) {
		while (node.parent != null && node.parent.right != node) {
			node = node.parent;
		}
		return node.parent;
	}
	
	private Node<T> getCurrent(Node<T> current) {

		return current.right != null ? getLeastFrom(current.right) : getGreaterParent(current);
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
		Node<T> current;
		Node<T> prev;
		boolean flNext = false;

		TreeIterator() {
			current = root == null ? null : getLeastFrom(root);
		}

		@Override
		public boolean hasNext() {

			return current != null;
		}
		


		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current.obj;
			prev = current;
			current = getCurrent(current);
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			removeNode(prev);
			flNext = false;
		}
	}
	
	private void removeNode(Node<T> node) {
		if (node.left != null && node.right != null) {
			removeJunction(node);
		} else {
			removeNonJunction(node);
		}
		size--;

	}
	
	private void removeJunction(Node<T> node) {
		Node<T> substitute = getGreatestFrom(node.left);
		node.obj = substitute.obj;
		removeNonJunction(substitute);

	}

	private void removeNonJunction(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> child = node.left == null ? node.right : node.left;
		if (parent == null) {
			root = child;
		} else {
			if (node == parent.left) {
				parent.left = child;
			} else {
				parent.right = child;
			}

		}
		if (child != null) {
			child.parent = parent;
		}
		node.setNulls();
		
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		return setEqualsTo(obj);
	}
	
	//new nodes
	@Override
	public Object clone() {
		return new TreeSet<T>(this);
	}

	
	@Override
	public SortedSet<T> headSetCopy(T toElement, boolean inclusive) {
		return subSetCopy(first(), true, toElement, inclusive);
	}
	

	@Override
	public SortedSet<T> tailSetCopy(T fromElement, boolean inclusive) {
		return subSetCopy(fromElement, inclusive, last(), true);
	}
	
	private Node<T> getParent(T key) {
		Node<T> node = getParentOrNode(key);
		Node<T> parent = null;
		
		if (comp.compare(key, node.obj) != 0) {
			parent = node;
		}
		return parent;
	}
		

	@Override
	public SortedSet<T> subSetCopy(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {
		TreeSet<T> target = new TreeSet<>();
		
		if (size() == 0)
			return target;
		
		var fromNode = this.ceilingNode(fromElement);
		var toNode = this.floorNode(toElement);	
		if (comp.compare(fromNode.obj, fromElement) == 0 && !fromInclusive) {
			fromNode = getNext(fromNode);		
		}
		
		do {
			target.add(fromNode.obj);
			fromNode = getNext(fromNode);
			
		} while(fromNode != toNode);
		
		if (comp.compare(fromNode.obj, toElement) != 0 || toInclusive)
			target.add(fromNode.obj);
		return target;
	}
	
	
	private Node<T> getNext(Node<T> prev) {		
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
	
	
	
	
	
	
	
	public void displayRotated() {
		displayRotated(root, 1);
	}

	private void displayRotated(Node<T> root, int level) {
		if (root != null) {
			displayRotated(root.right, level + 1);
			displayRoot(root, level);
			displayRotated(root.left, level + 1);
		}
		
		
	}

	private void displayRoot(Node<T> root, int level) {
		System.out.print(" ".repeat(level * spacesPerLevel));
		System.out.println(root.obj);
	}

	public int width() {	
		return width(root);
	}

	private int width(Node<T> root) {
		int res = 0;
		
		if (root != null) {
			if (root.left == null && root.right == null) {
				res = 1;
			} else {
				res = width(root.left) + width(root.right);
			}
		}
		
		return res;
	}

	public int height() {
		return height(root);
	}

	private int height(Node<T> root) {
		int res = 0;
		
		if (root != null) {
			int leftHeight = height(root.left);
			int rightHeight = height(root.right);
			res = Math.max(leftHeight, rightHeight) + 1;
		}
		return res;
	}

	public void balance() {
		Node<T>[] arrayNodes = getSortedArrayNodes();
		
		root = balanceArray(arrayNodes, 0, size - 1, null);
		
	}

	private Node<T> balanceArray(Node<T>[] arrayNodes, int left, int right, Node<T> parent) {
		Node<T> root = null;
		
		if (left <= right) {
			int rootIndex = (left + right) / 2;
			root = arrayNodes[rootIndex];
			root.parent = parent;
			root.left = balanceArray(arrayNodes, left, rootIndex - 1, root);
			root.right = balanceArray(arrayNodes, rootIndex + 1, right, root);
		}
		
		
		return root;
	}

	private Node<T>[] getSortedArrayNodes() {
		Node<T>[] res = new Node[size];
		
		int index = 0;
		Node<T> current = getLeastFrom(root);
		
		while (current != null) {
			res[index++] = current;
			current = getCurrent(current);
		}
		return res;
	}

	public void inverse() {
		var compNew = this.comp == null ? (Comparator<T>) Comparator.reverseOrder() : this.comp.reversed();
		TreeSet<T> ts = new TreeSet<>(this, compNew);
		this.root = ts.root;
		this.comp = ts.comp;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
