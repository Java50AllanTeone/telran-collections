package telran.additional;

public class AddList {
	
	public static class LinkedList {
		private class Node {
			Node next = null;
			String data;
			
			Node(String data) {
				this.data = data;
			}

			@Override
			public String toString() {
				return "Node [data=" + data + "]";
			}
			
			
		}
		
		Node head = null;
		
		void add(String obj) {
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
		
		Node getNode(int index) {
			Node res = head;
			for (int i = 0; i < index; i++) {
				res = res.next;
			}
			return res;
		}
		
		void setNext(Node current, Node target) {
			current.next = target;
		}
		
		void print() {
			Node node = this.head;
			do {
				System.out.println(node);
				node = node.next;
			}		
			while (node != null);
		}

		
		
		
		
		
		public static void main(String[] args) {
			var list = new LinkedList();
			list.add("0");
			list.add("1");
			list.add("2");
			list.add("3");
			list.add("4");
			list.add("5");
			list.add("6");
			list.add("7");
			list.add("8");
			list.add("9");
			
			System.out.println("After init");
			list.print();
			System.out.println();
			
			/*list.setNext(list.getNode(8), list.getNode(4));
			System.out.println("After looping");
			list.print();*/
		}
		
	}
}
