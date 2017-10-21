/**
 * A provided, partially complete, simple, recursive, singly-linked list for CPE
 * 103 Lab 6.
 *
 * @author Hatalsky/Jones (Provided skeleton).
 *
 *
 * @author Zeph Nord (Completed Class)
 * @version CPE 103 Lab 6
 * @version Winter 2017
 */
// Class invariants:
//
// The head reference is never null.
// The head node's next field will be null when the list is empty.
// The next field of the last node in the list is always null.
public class RecursiveLinkedList<T> implements SimpleList<T> {
	private class Node {
		public Node next;
		public T e;

		public Node() {
		}

		public Node(Node next, T e) {
			this.next = next;
			this.e = e;
		}
	}

	private Node head;
	private Throwable stackTrace;

	public RecursiveLinkedList() {
		head = new Node();
	}

	public void add(T element) {
		head.next = add(head.next, element);
	}

	public void addSimple(T element) {
		addSimple(head, element);
	}

	public void add(int index, T element) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		addNodeAtIndex(head, index, element);
	}

	public T get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return getNode(head.next, index);
	}

	public T remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return removeIndex(head, index);
	}

	public int size() {
		return size(head.next);
	}

	public Throwable stackTrace() {
		return stackTrace;
	}

	// Private recursive helper method for public add(T)
	private Node add(Node node, T e) {
		stackTrace = new Throwable();

		if (node == null) {
			return new Node(null, e);
		} else {
			node.next = add(node.next, e);
		}

		return node;
	}

	// Private ALTERNATE recursive helper method for public add(T)
	private void addSimple(Node node, T e) {
		stackTrace = new Throwable();

		if (node.next == null) {
			node.next = new Node(null, e);
		} else {
			addSimple(node.next, e);
		}
	}

	// Private recursive helper method for public size(int)
	private int size(Node node) {
		stackTrace = new Throwable();

		if (node == null) {
			return 0;
		}

		return 1 + size(node.next);
	}

	// Private recursive helper method for public get(int)
	private T getNode(Node node, int index) {
		stackTrace = new Throwable();

		if (index == 0) {
			return node.e;
		} else {
			return getNode(node.next, index - 1);
		}
	}

	// Private recursive helper method for public add(int, T)
	private void addNodeAtIndex(Node node, int index, T element) {
		stackTrace = new Throwable();

		if (node == null) {
			node = new Node(null, element);
		} else if (index == 0) { // current node is before insertion point
			Node addNode = new Node(node.next, element);
			node.next = addNode;
		} else {
			addNodeAtIndex(node.next, index - 1, element);
		}
	}

	// Private recursive helper method for public remove(int)
	private T removeIndex(Node node, int index) {
		stackTrace = new Throwable();

		// should we check for null even though its handled in public method?
		T tmpElement;
		if (index == 0) {
			// account for removing from index 0
			tmpElement = node.next.e;

			node.next = node.next.next;
			return tmpElement;
		} else {
			return removeIndex(node.next, index - 1);
		}
	}
}