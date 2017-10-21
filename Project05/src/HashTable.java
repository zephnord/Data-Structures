/**
 * Contains the interface for a generic hash table using an array as the underlying data structure
 * @author Zeph Nord
 * @version Project05
 * @version CPE103-03
 * @version Winter 2017
 */
public interface HashTable<T> {
	/**
	 * Returns true if this table contains the specified elements. This should
	 * take O(1) time on average.
	 * 
	 * @param element
	 *            - the element whose presence in this table is to be tested
	 * @return true if this table contains the specified element, false
	 *         otherwise
	 */
	boolean contains(T element);

	/**
	 * Returns true if this table contains no elements
	 * 
	 * @return true if this table contains no elements, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Adds the specified element to this table if an empty spot can be found.
	 * This should take O(1) time on average. *If the table already contains an
	 * equivalent element, the element in the table is replaced with the new
	 * element, and the old element is returned. If no equivalent element was
	 * already in the table, null is returned. *If adding a new element would
	 * cause the maximum load factor to be exceeded, the table will be rehashed
	 * (before the element is added) to a size that is the next prime larger
	 * than twice the current table size *There is an alternate spec that can be
	 * implemented for this method that is better
	 * 
	 * @param element
	 *            - the element to be added to this table
	 * @return the previous copy of the element from the table, or null if there
	 *         was no equivalent element already in the table
	 * @throws IllegalArgumentException
	 *             - if the element parameter is null
	 * @throws HashTableInsertionException
	 *             - if an empty spot in the hash table cannot be found
	 */
	T add(T element) throws IllegalArgumentException;

	/**
	 * Removes the specified element from this table if it is present. This
	 * should take O(1) time on average. Returns true if this table contains the
	 * element.
	 * 
	 * @param element - the key whose entry is to be removed from this table
	 * @return true if this table contained the specified element, otherwise false
	 * @throws IllegalArgumentException - if the element parameter is null
	 */
	boolean remove(T element) throws IllegalArgumentException;
	
	/**
	 * Returns the number of elements in this table
	 * 
	 * @return the number of elements in this table
	 */
	int size();
	
	/**
	 * Returns the current load factor of the hash table. That is the number of entries in the table divided by the size of the underlying array.
	 * @return the current load factor of the hash table
	 */
	double loadFactor();
	
	/**
	 * Returns the hash table size. That is the length of the underlying array.
	 * @return the hash table size
	 */
	int tableSize();
}
