import java.util.NoSuchElementException;

/**
 * Contains poorly implemented push and pop methods that add to the beginning of
 * an array based stack forcing shifts rather than using the end.
 * 
 * @author Zeph Nord
 * @version Lab04
 * @version CPE 103-03
 * @version Winter 2017
 */
public class BadStack<T> implements SimpleStack<T> {

	// Class field
	private T[] stack;
	private int size;

	// Class constructors
	@SuppressWarnings("unchecked")
	public BadStack() {
		this.size = 0;
		this.stack = (T[]) new Object[10];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleStack#peek()
	 */
	@Override
	public T peek() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return stack[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleStack#pop()
	 */
	@Override
	public T pop() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		T tmpElement = stack[0];
		for (int i = 1; i < size; i++) {
			stack[i - 1] = stack[i];
		}
		size--;
		return tmpElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleStack#push(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void push(T element) {
		if (size == stack.length) {
			T[] newStack = (T[]) new Object[2 * stack.length];
			for (int i = 0; i < stack.length; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
		}
		for (int i = size; i > 0; i--) {
			stack[i] = stack[i - 1];
		}
		stack[0] = element;
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleStack#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the max capacity of the stack array
	 * 
	 * @return the capacity of this stack
	 */
	public int capacity() {
		return stack.length;
	}
}
