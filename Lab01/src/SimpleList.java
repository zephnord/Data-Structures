/**
 * Contains the SimpleList interface
 * 
 * @author Zeph Nord
 * @version Lab01
 * @version CPE 102-03
 * @version Winter 2017
 */
public interface SimpleList<T> {
	/**
	 * Appends the specified element to the end of this list. Allows null
	 * elements to be added to this last.
	 * 
	 * @param element
	 *            - the element to be appended to this list
	 */
	public void add(T element);

	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and subsequent
	 * elements to the right (adds one to their indices)
	 * 
	 * @param index
	 *            - The index at which the element is to be inserted
	 * @param element
	 *            - The element to be inserted
	 */
	public void add(int index, T element);

	/**
	 * Returns the element at the specified position in the list
	 * 
	 * @param index
	 *            - the index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             - if the index is less than zero or greater than the size()
	 *             of this list
	 */
	public T get(int index);

	/**
	 * Removes the element at the specified position in the list. Shifts any
	 * subsequent elements to the left (subtracts on from their indices)
	 * 
	 * @param index
	 *            - The index of the element to be removed
	 * @return The element previously at the specified position (that was just
	 *         removed)
	 * @throws IndexOutOfBoundsException
	 *             - if the index is less that zero or greater than or equal to
	 *             the size() of this list
	 */
	public T remove(int index);

	/**
	 * Returns the number of elements in this list (elements that have been
	 * added by the creator/user of this list)
	 * 
	 * @return the number of elements in this list
	 */
	public int size();
}
