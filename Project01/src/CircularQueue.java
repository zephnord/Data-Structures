import java.util.NoSuchElementException;

/**
 * Contains the CircularQueue class that implements the SimpleQueue interface
 * Creates a generic array-based circular queue
 * 
 * @author Zeph Nord
 * @version Program1
 * @version CPE 103-03
 * @version Winter 2017
 */
public class CircularQueue<T> implements SimpleQueue<T> {

	// Class fields
	private T[] arr; // holds the elements in the queue
	private int back; // keeps track of the end of the queue
	private int front; // keeps track of the front of the queue
	public static final int INITIAL_LENGTH = 10;
	private int size; // Optional field, keeps track of the size of the queue

	// Class constructors

	/**
	 * Constructs and empty queue with an initials capacity given by
	 * INITIAL_LENGTH
	 */
	public CircularQueue() {
		this(INITIAL_LENGTH);
	}

	/**
	 * Constructs an empty queue with the specified initial capacity
	 * 
	 * @param initialCapacity
	 *            - the initial capacity to construct the queue
	 * @throws MyException
	 *             if the initial capacity is non-positive
	 */
	@SuppressWarnings("unchecked")
	public CircularQueue(int initialCapacity) {
		if (initialCapacity <= 0)
			throw new CircularQueue.MyException();
		this.arr = (T[]) new Object[initialCapacity];
		this.front = 0;
		this.back = -1;
		this.size = 0;
	}

	// Class methods

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleQueue#dequeue()
	 */
	@Override
	public T dequeue() throws RuntimeException {		
		if (size == 0) {
			throw new NoSuchElementException();
		}
		T temp;
		temp = arr[front];
		arr[front] = null;
		front++;
		size--;
		if (front == arr.length) {
			front = 0;
		}
		return temp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleQueue#enqueue(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(T element) {
		if (size == arr.length) {
			T[] newArr = (T[]) new Object[2 * arr.length];
			for (int i = 0; i < arr.length; i++) {
				if (front == arr.length)
					front = 0;
				newArr[i] = arr[front++];
			}
			back = arr.length - 1;
			arr = newArr;
			front = 0;
		} else if (back == arr.length - 1) {
			back = -1;
		}
		arr[++back] = element;
		size++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleQueue#peek()
	 */
	@Override
	public T peek() throws RuntimeException {
		if (size == 0)
			throw new NoSuchElementException();
		return arr[front];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleQueue#size()
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns a reference to the private arr. This is (as the name would imply)
	 * for testing purposes only. You should never do this in practice.
	 * 
	 * @return a reference to arr
	 */
	public Object[] unusualMethodForTestingPurposesOnly() {
		return arr;
	}

	// Nested custom exception class
	@SuppressWarnings("serial")
	static public class MyException extends RuntimeException {
		/**
		 * Constructs a MyException with no detail message
		 */
		public MyException() {
			super();
		}

		/**
		 * Constructs a MyException with the specified detail message
		 * 
		 * @param s
		 *            - the detail message
		 */
		public MyException(String s) {
			super(s);
		}
	}

}
