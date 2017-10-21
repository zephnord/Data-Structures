/**
 * Contains the SimpleQueue interface
 * @author Zeph Nord
 * @version Lab02
 * @version CPE 103-03
 * @version Winter 2017
 */
public interface SimpleQueue<T> {
	/**
	 * Removes and returns the elements at the front of this queue
	 * @return the element that was removed from the front of this queue
	 * @throws NoSuchElementException - if this queue is empty
	 */
	public T dequeue() throws RuntimeException;
	
	/**
	 * Adds the specified element to the end of this queue
	 * @param element - the element to add to the end of this queue
	 */
	public void enqueue(T element);
	
	/**
	 * Returns the element at the front of this queue (but does not remove it)
	 * @return the element at the front of this queue
	 * @throws NoSuchElementException - if this queue is empty
	 */
	public T peek() throws RuntimeException;
	
	/**
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size();
}
