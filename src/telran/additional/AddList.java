package telran.additional;

import java.util.LinkedHashSet;

public class AddList {

	public static class LinkedList {
		private class Node {
			Node next = null;
			int data;

			Node(int data) {
				this.data = data;
			}

			@Override
			public String toString() {
				return "Node [data=" + data + "]";
			}


		}

		Node head = null;

		public void add(int obj) {
			Node node = new Node(obj);
			Node nodePrev = head;
			if (nodePrev == null) {
				head = node;
				return;
			} else {
				while (nodePrev.next != null) {
					nodePrev = nodePrev.next;
				}
			}
			nodePrev.next = node;
		}

		public Node getNode(int index) {
			Node res = head;
			for (int i = 0; i < index; i++) {
				res = res.next;
			}
			return res;
		}

		public void setNext(Node current, Node target) {
			current.next = target;
		}

		public boolean floydCheck() {
			Node slow = head;
			Node fast = head;

			while (fast.next != null && fast.next.next != null) {
				slow = slow.next;
				fast = fast.next.next;
				if (fast == slow)
					return true;
			}
			return false;
		}

		public int getIndexFloyd() {
			Node slow = head;
			Node fast = head;
			int index = 0;

			while (fast.next != null && fast.next.next != null) {
				slow = slow.next;
				fast = fast.next.next;
				if (fast == slow) {
					slow = head;

					while (slow != fast) {
						slow = slow.next;
						fast = fast.next;
						index++;
					}
					return index;
				}
			}
			return -1;
		}

		public int getIndexHashSet() {
			var set = new LinkedHashSet<>();
			Node pointer = head;

			do {
				if (!set.add(pointer)) {
					int index = 0;
					for (var elem : set) {
						if (elem == pointer)
							return index;
						index++;
					}
				}
				pointer = pointer.next;
			} while (pointer != null);

			return -1;
		}


		//very slow
		//destroys root list
		//doesn't check if the list has loop
		//not finished
		public int getIndexWrapper() {
			class MyNode {
				Node data;
				MyNode next;

				MyNode(Node data) {
					this.data = data;
				}

				int indexOf(MyNode start, Node data) {
					int index = 0;
					while (data != start.data) {
						start = start.next;
						index++;
					}
					return index;
				}
			}

			int index = 0;
			MyNode myHead = new MyNode(head);
			MyNode cur = myHead;
			Node pointer = head;
			Node temp;

			while (pointer.next != null) {
				temp = pointer;
				pointer = pointer.next;
				temp.next = null;
				cur.next = new MyNode(pointer);
				cur = cur.next;
			}
			cur = myHead;

			while (pointer != cur.data) {
				cur = cur.next;
				index++;
			}
			return index;
		}
	}
}
