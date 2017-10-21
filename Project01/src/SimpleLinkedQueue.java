import java.util.NoSuchElementException;

/**
 * Contains the SimpleLinkedQueue class that implements the SimpleQueue interface
 * Creates a Doubly LinkedQueue with two dummy nodes
 * @author Zeph Nord
 * @version Lab02
 * @version CPE 103-03
 * @version Winter 2017
 */
public class SimpleLinkedQueue<T> implements SimpleQueue<T> {
	private Node head;
	private Node tail;
	private int size;	

	public SimpleLinkedQueue() {
		this.head = new Node(null);
		this.tail = new Node(null);
		this.head.next = tail;
		this.tail.previous = head;
	}

	/* (non-Javadoc)
	 * @see SimpleQueue#dequeue()
	 */
	public T dequeue() throws RuntimeException {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		
		T tmpElement = head.next.element;
		head.next = head.next.next;
		head.next.previous = head;
		size--;
		
		return tmpElement;
	}

	/* (non-Javadoc)
	 * @see SimpleQueue#enqueue(java.lang.Object)
	 */
	public void enqueue(T element) {
		Node node = new Node(element);
		
		node.previous = tail.previous;
		node.next = tail;
		node.previous.next = node;
		tail.previous = node;
		size++;
	}

	/* (non-Javadoc)
	 * @see SimpleQueue#peek()
	 */
	public T peek() throws RuntimeException {
		if(size == 0) {
			throw new NoSuchElementException();
		}

		return head.next.element;
	}

	/* (non-Javadoc)
	 * @see SimpleQueue#size()
	 */
	public int size() {
		return size;
	}	

	private class Node{
		private Node next;
		private Node previous;
		private T element;
		
		public Node(T element) {
			this.next = null;
			this.previous = null;
			this.element = element;
		}
	}
}
