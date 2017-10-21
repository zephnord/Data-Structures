import java.util.NoSuchElementException;

/**
 * @author Zeph Nord
 * @version Program02 
 * @version CPE 103-03
 * @version Winter 2017
 */
public class SimpleLinkedStack<T> implements SimpleStack<T> {
	//Class fields
	private Node top;
	private int size;
	
	//Class constructors
	public SimpleLinkedStack() {
		this.top = null;
		this.size = 0;
	}

	/* (non-Javadoc)
	 * @see SimpleStack#peek()
	 */
	@Override
	public T peek() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return top.element;
	}

	/* (non-Javadoc)
	 * @see SimpleStack#pop()
	 */
	@Override
	public T pop() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		T tmpElement = top.element;
		top = top.previous;
		size--;
		return tmpElement;
	}

	/* (non-Javadoc)
	 * @see SimpleStack#push(java.lang.Object)
	 */
	@Override
	public void push(T element) {
		Node node = new Node(element);
		node.previous = top;
		top = node;
		size++;
	}

	/* (non-Javadoc)
	 * @see SimpleStack#size()
	 */
	@Override
	public int size() {
		return size;
	}
	
	private class Node {
		//Inner class fields
		private Node previous;
		private T element;
		
		//Inner class constructor
		private Node(T element) {
			this.previous = null;
			this.element = element;
		}
	}

}
