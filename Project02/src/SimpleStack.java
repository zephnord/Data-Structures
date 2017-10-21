import java.util.NoSuchElementException;

/**
 * A simple stack interface
 * @author Zeph Nord
 * @version Program02
 * @version CPE 103-03
 * @version Winter 2017
 */
public interface SimpleStack<T> {
	/**
	 * Returns the element on the top of this stack (but does not remove it)
	 * @return the element on the top of this stack
	 * @throws NoSuchElementException - if this stack is empty
	 */
	public T peek() throws NoSuchElementException;
	
	/**
	 * Removes and returns the element on the top of this stack
	 * @return the element on the top of this stack
	 * @throws NoSuchElementException - if this stack is empty
	 */
	public T pop() throws NoSuchElementException;
	
	/**
	 * Adds the specified element to the top of this stack
	 * @param element - the element to add to the top of this stack
	 */
	public void push(T element);
	
	/**
	 * The number of elements in this stack
	 * @return the number of elements in this stack
	 */
	public int size(); 
}
