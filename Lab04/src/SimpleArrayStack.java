import java.util.NoSuchElementException;

/**
 * Array based implementation of a simple stack
 * @author Zeph Nord
 * @version CPE 103-03
 * @version Winter 2017
 * @version Lab03
 */
public class SimpleArrayStack<T> implements SimpleStack<T> {
	
	//Class field
	private T[] stack;
	private int size;
	
	//Class constructors
	@SuppressWarnings("unchecked")
	public SimpleArrayStack() {
		this.size = 0;	
		this.stack =  (T[]) new Object[10];
	}

	/* (non-Javadoc)
	 * @see SimpleStack#peek()
	 */
	@Override
	public T peek() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return stack[size - 1];
	}

	/* (non-Javadoc)
	 * @see SimpleStack#pop()
	 */
	@Override
	public T pop() {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		T tmpElement = stack[size - 1];
		stack[size - 1] = null;
		size--;
		return tmpElement;
	}

	/* (non-Javadoc)
	 * @see SimpleStack#push(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void push(T element) {
		if(size == stack.length) {
			T[] newStack = (T[]) new Object[2 * stack.length];
			for(int i = 0; i < stack.length; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
		}
		stack[size] = element;
		size++;		
	}

	/* (non-Javadoc)
	 * @see SimpleStack#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the max capacity of the stack array
	 * @return the capacity of this stack
	 */
	public int capacity() {
		return stack.length;
	}
}
